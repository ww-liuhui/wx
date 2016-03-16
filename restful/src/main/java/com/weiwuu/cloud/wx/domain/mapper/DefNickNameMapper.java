package com.weiwuu.cloud.wx.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.weiwuu.cloud.wx.domain.DefNickName;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


public class DefNickNameMapper implements ResultSetMapper<DefNickName>
{
    public DefNickName map(int index, ResultSet r, StatementContext ctx) throws SQLException
    {
        return new DefNickName(r.getInt("id"), r.getString("name"), r.getInt("use_num"));
    }
}
