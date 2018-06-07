package data;

import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {

    public DataSource createDataSource() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:nomagic.db");
        return dataSource;
    }
}
