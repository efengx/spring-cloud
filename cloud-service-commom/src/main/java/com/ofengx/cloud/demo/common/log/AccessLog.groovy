package com.ofengx.cloud.demo.common.log;

/**
 * 用户访问日志
 *
 * @version
 * @author zengjq  2016年10月25日 下午5:14:38
 *
 */
class AccessLog {
    /**用户访问ip地址*/
    String ip
    /**用户访问url地址*/
    String url
    /**用户会话sid*/
    String sid
    /**用户id*/
    String userId
    /**用户ua信息*/
    String ua
    /**终端类型*/
    String terminalType
    /**终端型号*/
    String terminalName
    /**渠道号*/
    String channelId
    /**设备ID*/
    String devicesId

    String getIp() {
        return ip
    }

    void setIp(String ip) {
        this.ip = ip
    }

    String getUrl() {
        return url
    }

    void setUrl(String url) {
        this.url = url
    }

    String getSid() {
        return sid
    }

    void setSid(String sid) {
        this.sid = sid
    }

    String getUserId() {
        return userId
    }

    void setUserId(String userId) {
        this.userId = userId
    }

    String getUa() {
        return ua
    }

    void setUa(String ua) {
        this.ua = ua
    }

    String getTerminalType() {
        return terminalType
    }

    void setTerminalType(String terminalType) {
        this.terminalType = terminalType
    }

    String getTerminalName() {
        return terminalName
    }

    void setTerminalName(String terminalName) {
        this.terminalName = terminalName
    }

    String getChannelId() {
        return channelId
    }

    void setChannelId(String channelId) {
        this.channelId = channelId
    }

    String getDevicesId() {
        return devicesId
    }

    void setDevicesId(String devicesId) {
        this.devicesId = devicesId
    }
}
