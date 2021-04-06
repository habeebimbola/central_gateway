package com.centralgateway.task.dao;

import com.centralgateway.task.DVDActor;
import com.centralgateway.task.service.GlobalDAOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class GlobalDAO extends JdbcDaoSupport implements GlobalDAOService<DVDActor> {

    private final Logger logger = LoggerFactory.getLogger(GlobalDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private final String saveSQL = "INSERT INTO ACTOR(FIRST_NAME, LAST_NAME) values(?,?)";
    private final String selectSQL = "SELECT * FROM ACTOR WHERE actor_id =?";
    private final String updateSQL = "UPDATE ACTOR SET FIRST_NAME =?, LAST_NAME =? WHERE actor_id =?";
    private final String selectActorsSQL = "SELECT * FROM ACTOR order by actor_id desc limit 20";

    @Autowired
    public GlobalDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(DVDActor dvdActor) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(saveSQL)) {
            preparedStatement.setString(1, dvdActor.getFirstName());
            preparedStatement.setString(2, dvdActor.getLastName());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public DVDActor getOne(Long id) {
        DVDActor dvdActor = null;
        try(PreparedStatement preparedStatement = this.dataSource.getConnection().prepareStatement(selectSQL)) {
            preparedStatement.setLong(1, id);

            dvdActor = toDVDACtor(preparedStatement.executeQuery())  ;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dvdActor;
    }
    @Override
    public void update(DVDActor dvdActor) {
      try(PreparedStatement preparedStatement = this.dataSource.getConnection().prepareStatement(updateSQL))
      {
          preparedStatement.setString(1, dvdActor.getFirstName());
          preparedStatement.setString(2, dvdActor.getLastName());
          preparedStatement.setInt(3, dvdActor.getId());
          preparedStatement.executeUpdate();

      }  catch (SQLException throwables) {
          throwables.printStackTrace();
      }
    }

    @Override
    public List<DVDActor> findAll() {
        List<DVDActor> actorList = Collections.emptyList();

        try(PreparedStatement preparedStatement = this.dataSource.getConnection().prepareStatement(selectActorsSQL))
        {
            actorList = toActorsList(preparedStatement.executeQuery()) ;

        }catch(SQLException exception)
        {
            exception.printStackTrace();
        }
        return actorList;
    }

    @Override
    public void updateList(List<DVDActor> list) {

        BeanPropertyRowMapper.newInstance(DVDActor.class);


        DVDActor dvdActor = getJdbcTemplate().queryForObject(this.selectSQL, BeanPropertyRowMapper.newInstance(DVDActor.class));
//        jdbcTemplate.batchUpdate(this.saveSQL, list, list.size(), );
    }

    private List<DVDActor> toActorsList(ResultSet executeQuery) throws SQLException {
        List<DVDActor> actorList = new ArrayList<>();

        while(executeQuery.next()) {
            DVDActor dvdActor = new DVDActor();
            dvdActor.setFirstName(executeQuery.getString("FIRST_NAME"));
            dvdActor.setLastName(executeQuery.getString("LAST_NAME"));
            dvdActor.setId(executeQuery.getInt("ACTOR_ID"));
            actorList.add(dvdActor);
        }
        return actorList;
    }

    private DVDActor toDVDACtor(ResultSet executeQuery) throws SQLException {
        DVDActor dvdActor = new DVDActor();
        dvdActor.setFirstName(executeQuery.getString("FIRST_NAME"));
        dvdActor.setLastName(executeQuery.getString("LAST_NAME"));
        dvdActor.setId(executeQuery.getInt("ACTOR_ID"));
        return dvdActor;
    }



}
