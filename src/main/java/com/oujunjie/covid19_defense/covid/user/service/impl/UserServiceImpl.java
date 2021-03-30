package com.oujunjie.covid19_defense.covid.user.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.oujunjie.covid19_defense.comm.base.CommonResult;
import com.oujunjie.covid19_defense.comm.my_exception.exceptions.POIException;
import com.oujunjie.covid19_defense.comm.my_exception.exceptions.UserException;
import com.oujunjie.covid19_defense.comm.utils.POIUtils;
import com.oujunjie.covid19_defense.covid.user.dao.UserDao;
import com.oujunjie.covid19_defense.covid.user.entity.po.User;
import com.oujunjie.covid19_defense.covid.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao userDao;

    @Override
    public CommonResult uploadUserByExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            List<Object[]> list = POIUtils.handerImportExcel(inputStream);
            ArrayList<User> users = new ArrayList<>();
            ArrayList<String> uids = new ArrayList<>();

            for (int i = 0; i < list.size(); i++) {
                String name = (String) list.get(i)[0];
                String uid = list.get(i)[1].toString();
                String college = (String) list.get(i)[2];
                String grade = (String) list.get(i)[3];

                // 判断当前Excel uid是否重复
                if (uids.contains(uid)) {
                    throw new UserException("uid: " + uid + "conflict");
                }
                uids.add(uid);

                User dbUser = this.getUserByUid(uid);
                if (dbUser != null)
                    throw new UserException(" uid: " + uid + " already exist");

                users.add(new User()
                        .setName(name)
                        .setUid(uid)
                        .setCollege(college)
                        .setGrade(grade)
                );
            }

            this.insertUsers(users);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new POIException("Data format error,please check excel data");
        }
        return null;
    }

    public User getUserByOpenId(String openId) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("openId", openId);
        List<User> users = userDao.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }


    @Override
    public CommonResult BindUser(String openId, String uid) {
        User dbUser = this.getUserByUid(uid);
        if (dbUser == null) {
            throw new UserException("please check uid");
        }else if (!dbUser.getOpenId().equals("")){
            throw new UserException("current user had bind");
        }
        dbUser.setOpenId(openId);

        userDao.updateByPrimaryKey(dbUser);
        return CommonResult.success();
    }


    @Transactional
    public void insertUsers(ArrayList<User> users) {
        users.forEach(e -> {
            e.setPassword(SecureUtil.md5().digestHex("123456"));
            e.setRole(0);
            e.setCtime(new Date());
            e.setMtime(new Date());
            e.setOpenId("");
            userDao.insert(e);
        });
    }

    public User getUserByUid(String uid) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uid", uid);

        List<User> users = userDao.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        } else return null;
    }
}
