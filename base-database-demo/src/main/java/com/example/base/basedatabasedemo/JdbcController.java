package com.example.base.basedatabasedemo;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 新建测试类连接数据库
 *
 * @author qiw-a
 * @date 2019/4/30
 */
@RestController
@RequestMapping("/jdbc")
public class JdbcController {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/userlist")
    public List<User> getUserList(ModelMap map) {
        String sql = "SELECT * FROM user";
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            User user = null;

            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setSex(rs.getString("sex"));
                user.setAge(rs.getInt("age"));
                return user;

            }
        });
        for (User user : userList) {
            System.out.println(user.getName());
        }
        map.addAttribute("users", userList);

        return userList;
    }

    @GetMapping
    public int[] batchInsert() {
        String sql = "INSERT INTO User(name, sex, age) VALUES('李彬','男',26)";
//        for (int i = 0; i < 2; i++) {
//            sql += sql;
//        }
        String substring = sql.substring(0, sql.length() - 1);
        return jdbcTemplate.batchUpdate(new String[]{sql,sql});
    }

}
