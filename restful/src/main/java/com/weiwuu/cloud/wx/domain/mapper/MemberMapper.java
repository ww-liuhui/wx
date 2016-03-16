package com.weiwuu.cloud.wx.domain.mapper;

import com.weiwuu.cloud.wx.domain.Member;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberMapper implements ResultSetMapper<Member>
{

    @Override
    public Member map(int index, ResultSet r, StatementContext ctx) throws SQLException
    {
        return new Member(r.getInt("id"), r.getInt("type_id"), r.getString("code"), r.getString("name"),r.getString
                ("avatar_url"), r.getString("mobile"),r.getString("weixin"), r.getInt("city_id"));

    }
}
