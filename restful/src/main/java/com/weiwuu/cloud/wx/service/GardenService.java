package com.weiwuu.cloud.wx.service;

import com.weiwuu.cloud.wx.dao.GardenDAO;
import com.weiwuu.cloud.wx.domain.DefNickName;
import com.weiwuu.cloud.wx.domain.Member;
import com.weiwuu.cloud.wx.entity.response.CityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GardenService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GardenService.class);
    private final GardenDAO dao;

    public GardenService(GardenDAO dao)
    {
        this.dao = dao;
    }

    /***
     * 获取城市列表
     * @return
     */
    public List<CityResponse> getCityList()
    {
        List<CityResponse> cityList = null;
        cityList = dao.getCityList();
        return cityList;
    }

    public Member getMember(int authorId)
    {
        Member member = null;
        member = dao.getMember(authorId);
        return member;
    }

    public int recomRegister(String mobile,String code)
    {
        int flag = 0;
        if(code!=null&&isMobileNO(code)){
            Member member = null;
            String name = null;
            try
            {
                // 默认昵称
                DefNickName defNickName = dao.getDefNickName();
                // 随机昵称，请使用
                name = defNickName.getName() + defNickName.getUse_num();
                // 昵称计数加1
                dao.modifyDefNickNum(defNickName.getId());

                member = dao.getBrokerByCode(mobile);
                if(member==null){
                    String recomcode = null;
                    member = dao.getMemberByCode(mobile);
                    if(null==member){  //未注册
                        int randomCode = (int) (Math.random() * 900000 + 100000);//六位随机验证码
                        //注册
                        dao.recomRegister(mobile,code,randomCode,new Date(),name);
                        flag = 1;
                    }else{  //已注册
                        recomcode = dao.getRecomcodeByCode(mobile);
                        if(recomcode==null||recomcode.equals("")){ //未被推荐
                            //更新被推荐人信息
                            dao.updateMemberRecomcode(mobile,code);
                            flag = 1;
                        }else{
                            flag = 2;
                        }
                    }

                }

            }catch (Exception e){
                LOGGER.error("erro:"+e.getMessage());
            }
        }
        return flag;
    }

    public int createMember(String code,String name,String avatar_url)
    {
        int flag = 0;
        Member member = null;
        Member broker = null;
        int adviserid = 0;
        try
        {
            member = dao.getMemberByCode(code);
            broker = dao.getBrokerByCode(code);
            adviserid = dao.getAdviserIdByCode(code);
            if(null==member){
                if(null==broker&&adviserid==0){
                    dao.createMember(code,name,avatar_url,new Date());
                    flag = 1;//该号码没有注册
                }else{
                    flag = 3;//该号码注册过经纪人或置业顾问
                }

            }else{
                flag = 2;//该号码注册过买房人
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /***
     * 预约看房
     * @return
     */
    public int createOrder(String name,String tel,String time,String addr_from,String addr_to)
    {
        int flag = 0;
        flag = dao.createOrder(name,tel,time,addr_from,addr_to,new Date());
        return flag;
    }

    /***
     * 判断是否为手机号
     * @param mobiles
     * @return
     */
    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^1\\d{10}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
