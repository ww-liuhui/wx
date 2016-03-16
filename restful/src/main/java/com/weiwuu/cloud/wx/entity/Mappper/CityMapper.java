package com.weiwuu.cloud.wx.entity.Mappper;

import com.weiwuu.cloud.wx.entity.response.CityResponse;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper implements ResultSetMapper<CityResponse>
{

    @Override
    public CityResponse map(int index, ResultSet r, StatementContext ctx) throws SQLException
    {
        return new CityResponse(r.getInt("id"), r.getString("name"), r.getString("abbreviation"),0);
    }

}
