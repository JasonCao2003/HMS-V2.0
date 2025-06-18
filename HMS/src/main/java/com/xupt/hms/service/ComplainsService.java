package com.xupt.hms.service;

import com.xupt.hms.pojo.Complains;

import java.util.List;

public interface ComplainsService {
    Boolean complainsAdd(Complains article);

    List<Complains> complainsList(String title);

    List<Complains> listComplainsByUserid(Integer userid);

    Boolean complainsDelete(Integer complainsid);

    Boolean complainsUpdate(Complains complains);

    Complains SelectById(Integer complainsid);
}
