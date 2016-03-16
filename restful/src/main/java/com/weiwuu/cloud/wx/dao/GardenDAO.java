package com.weiwuu.cloud.wx.dao;

import com.weiwuu.cloud.wx.domain.DefNickName;
import com.weiwuu.cloud.wx.domain.Member;
import com.weiwuu.cloud.wx.domain.mapper.DefNickNameMapper;
import com.weiwuu.cloud.wx.domain.mapper.MemberMapper;
import com.weiwuu.cloud.wx.entity.Mappper.CityMapper;
import com.weiwuu.cloud.wx.entity.response.CityResponse;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.util.Date;
import java.util.List;

public abstract class GardenDAO implements Transactional<GardenDAO>
{
    @SqlQuery("select last_insert_id()")
    public abstract int findLastInsertId();

    @Mapper(CityMapper.class)
    @SqlQuery("select * from city where displayOrder<200")
    public abstract List<CityResponse> getCityList();

    @Mapper(MemberMapper.class)
    @SqlQuery("select * from member where id=:authorId")
    public abstract Member getMember(@Bind("authorId") int authorId);

    @SqlQuery("select id from city where name like concat('%',:cityName,'%')")
    public abstract int getCityIdByName(@Bind("cityName") String cityName);

    @SqlQuery("select id from broker where code=:mobile")
    public abstract int geUseridByMobile(@Bind("mobile") String mobile);

    @SqlUpdate("insert into member_recommend(code,re_code,re_type_id,create_time) values(:code,:re_code,:re_type_id,:create_time)")
    public abstract int createMemberRecommend(@Bind("code") String code, @Bind("re_code") String re_code, @Bind("re_type_id") int re_type_id, @Bind("create_time") Date create_time);

    @Mapper(MemberMapper.class)
    @SqlQuery("select * from member where code=:code")
    public abstract Member getMemberByCode(@Bind("code") String code);

    @Mapper(MemberMapper.class)
    @SqlQuery("select * from broker where code=:code")
    public abstract Member getBrokerByCode(@Bind("code") String code);

    @SqlUpdate("insert into member(type_id,code,mobile,password,created_time,recomcode,rand_flag,name,nick_name) values(3,:code,:code,:randomCode',:created_time,:recomcode,0,:name,:name)")
    public abstract int recomRegister(@Bind("code") String code, @Bind("recomcode") String recomcode, @Bind("randomCode") int randomCode, @Bind("create_time") Date create_time,@Bind("name") String name);

    @SqlQuery("select recomcode from member where code=:code")
    public abstract String getRecomcodeByCode(@Bind("code") String code);

    @SqlUpdate("update member set recomcode=:recomcode where code=:code")
    public abstract int updateMemberRecomcode(@Bind("code") String code, @Bind("recomcode") String recomcode);

    @SqlUpdate("UPDATE default_nickname SET use_num = use_num + 1 WHERE id = :id")
    public abstract void modifyDefNickNum(@Bind("id")int id);

    @Mapper(DefNickNameMapper.class)
    @SqlQuery("SELECT * FROM default_nickname AS dn ORDER BY dn.use_num LIMIT 1")
    public abstract DefNickName getDefNickName();

    @SqlUpdate("insert into member(type_id,code,mobile,password,created_time,rand_flag,name,nick_name,avatar_url) values(3,:code,:code,'123456',:date,0,:name,:name,:avatar_url)")
    public abstract int createMember(@Bind("code") String code, @Bind("name") String name,@Bind("avatar_url") String avatar_url,@Bind("date") Date date);

    @SqlQuery("select id from adviser where code=:code")
    public abstract int getAdviserIdByCode(@Bind("code") String code);

    @SqlUpdate("insert into wxorder(name,tel,time,addr_from,addr_to,create_time) values(:name,:tel,:time,:addr_from,:addr_to,:create_time)")
    public abstract int createOrder(@Bind("name") String name, @Bind("tel") String tel,@Bind("time") String time,@Bind("addr_from") String addr_from,@Bind("addr_to") String addr_to,@Bind("create_time") Date create_time);

}
