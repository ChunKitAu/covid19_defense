package com.oujunjie.covid19_defense.covid.user.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.oujunjie.covid19_defense.comm.base.CommonResult;
import com.oujunjie.covid19_defense.comm.my_exception.exceptions.POIException;
import com.oujunjie.covid19_defense.comm.my_exception.exceptions.UserException;
import com.oujunjie.covid19_defense.comm.utils.POIUtils;
import com.oujunjie.covid19_defense.covid.user.dao.UserDao;
import com.oujunjie.covid19_defense.covid.user.entity.po.User;
import com.oujunjie.covid19_defense.covid.user.service.UserService;
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

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public CommonResult uploadUserByExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            List<Object[]> list = POIUtils.handerImportExcel(inputStream);
            ArrayList<User> users = new ArrayList<>();

            for (int i = 0; i < list.size(); i++) {
                String name = (String) list.get(i)[0];
                String uid = list.get(i)[1].toString();

                User dbUser = this.getUserByNameAndUid(name, uid);
                if (dbUser != null)
                    throw new UserException("user:" + name + " uid: " + uid + " already exist");

                users.add(new User().setName(name).setUid(uid));
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

    @Transactional
    public void insertUsers(ArrayList<User> users) {
        users.forEach(e -> {
            e.setPassword(SecureUtil.md5().digestHex("123456"));
            e.setRole(0);
            e.setCtime(new Date());
            e.setMtime(new Date());
            userDao.insert(e);
        });
    }


    public User getUserByNameAndUid(String name, String uid) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", name);
        criteria.andEqualTo("uid", uid);

        List<User> users = userDao.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        } else return null;
    }
}
