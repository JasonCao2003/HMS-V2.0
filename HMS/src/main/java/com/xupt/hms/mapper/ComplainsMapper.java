package com.xupt.hms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xupt.hms.pojo.Complains;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplainsMapper extends BaseMapper<Complains> {
    @Update("update complains set status = #{status}, reps = #{reps} where complainsid = #{complainsid} ")
    int update(@Param("status") String status, @Param("reps") String reps, @Param("complainsid") Integer complainsid);
}
