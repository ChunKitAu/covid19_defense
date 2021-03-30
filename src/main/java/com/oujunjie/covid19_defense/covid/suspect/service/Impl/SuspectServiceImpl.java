package com.oujunjie.covid19_defense.covid.suspect.service.Impl;

import com.oujunjie.covid19_defense.covid.suspect.dao.SuspectDao;
import com.oujunjie.covid19_defense.covid.suspect.entity.po.Suspect;
import com.oujunjie.covid19_defense.covid.suspect.service.SuspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @auther ChunKitAu
 * @create 2021-03-29 29
 */
@Service
public class SuspectServiceImpl implements SuspectService {

    @Autowired
    SuspectDao suspectDao;

    @Override
    public boolean insertSupect(Long userId, Long positionId, Long cityId) {
        int insert = suspectDao.insert(new Suspect()
                .setCityId(cityId)
                .setCtime(new Date())
                .setPositionId(positionId)
                .setUserId(userId)
        );
        if (insert > 0)
            return true;
        return false;
    }
}
