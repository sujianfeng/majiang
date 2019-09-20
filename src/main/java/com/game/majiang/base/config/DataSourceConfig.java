package com.game.majiang.base.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.apache.log4j.Logger;

/**
 * Created by peter on 16/8/19.
 */

public abstract class DataSourceConfig {

    protected String url;
    protected String username;
    protected String password;
    protected String driverClass;
    protected Integer idleMaxAgeInMinutes;
    protected Integer idleConnectionTestPeriodInMinutes;
    protected Integer maxConnectionsPerPartition;
    protected Integer minConnectionsPerPartition;
    protected Integer partitionCount;
    protected Integer acquireIncrement;
    protected Integer statementsCacheSize;
    private Logger logger = Logger.getLogger(DataSourceConfig.class);

    public void initDataSource(BoneCPDataSource dataSource) {
        dataSource.setJdbcUrl(url);
        dataSource.setDriverClass(driverClass);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setIdleConnectionTestPeriodInMinutes(idleConnectionTestPeriodInMinutes);
        dataSource.setIdleMaxAgeInMinutes(idleMaxAgeInMinutes);
        dataSource.setMaxConnectionsPerPartition(maxConnectionsPerPartition);
        dataSource.setMinConnectionsPerPartition(minConnectionsPerPartition);
        dataSource.setPartitionCount(partitionCount);
        dataSource.setAcquireIncrement(acquireIncrement);
        dataSource.setStatementsCacheSize(statementsCacheSize);

        dataSource.setConnectionTestStatement("SELECT 1");
        dataSource.setIdleConnectionTestPeriodInMinutes(1);

        logger.info(toString());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public Integer getIdleMaxAgeInMinutes() {
        return idleMaxAgeInMinutes;
    }

    public void setIdleMaxAgeInMinutes(Integer idleMaxAgeInMinutes) {
        this.idleMaxAgeInMinutes = idleMaxAgeInMinutes;
    }

    public Integer getIdleConnectionTestPeriodInMinutes() {
        return idleConnectionTestPeriodInMinutes;
    }

    public void setIdleConnectionTestPeriodInMinutes(Integer idleConnectionTestPeriodInMinutes) {
        this.idleConnectionTestPeriodInMinutes = idleConnectionTestPeriodInMinutes;
    }

    public Integer getMaxConnectionsPerPartition() {
        return maxConnectionsPerPartition;
    }

    public void setMaxConnectionsPerPartition(Integer maxConnectionsPerPartition) {
        this.maxConnectionsPerPartition = maxConnectionsPerPartition;
    }

    public Integer getMinConnectionsPerPartition() {
        return minConnectionsPerPartition;
    }

    public void setMinConnectionsPerPartition(Integer minConnectionsPerPartition) {
        this.minConnectionsPerPartition = minConnectionsPerPartition;
    }

    public Integer getPartitionCount() {
        return partitionCount;
    }

    public void setPartitionCount(Integer partitionCount) {
        this.partitionCount = partitionCount;
    }

    public Integer getAcquireIncrement() {
        return acquireIncrement;
    }

    public void setAcquireIncrement(Integer acquireIncrement) {
        this.acquireIncrement = acquireIncrement;
    }

    public Integer getStatementsCacheSize() {
        return statementsCacheSize;
    }

    public void setStatementsCacheSize(Integer statementsCacheSize) {
        this.statementsCacheSize = statementsCacheSize;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DataSourceConfig{");
        sb.append("url='").append(url).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", driverClass='").append(driverClass).append('\'');
        sb.append(", idleMaxAgeInMinutes=").append(idleMaxAgeInMinutes);
        sb.append(", idleConnectionTestPeriodInMinutes=").append(idleConnectionTestPeriodInMinutes);
        sb.append(", maxConnectionsPerPartition=").append(maxConnectionsPerPartition);
        sb.append(", minConnectionsPerPartition=").append(minConnectionsPerPartition);
        sb.append(", partitionCount=").append(partitionCount);
        sb.append(", acquireIncrement=").append(acquireIncrement);
        sb.append(", statementsCacheSize=").append(statementsCacheSize);
        sb.append('}');
        return sb.toString();
    }
}
