package org.liuxinyi.utils.sftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * Created by Eric.Liu on 2016/9/6.
 */
@Slf4j
public class SftpUtil {

    public static ChannelSftp connect(String host, int port, String username, String password) {
        try {
            JSch jSch = new JSch();
            Session session = jSch.getSession(username, host, port);
            session.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            session.setConfig(sshConfig);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            return (ChannelSftp) channel;
        } catch (Exception e) {
            log.error("failed to connect sftp", e);
            return new ChannelSftp();
        }
    }

    public static void disconnect(ChannelSftp channelSftp) {
        if (channelSftp != null && channelSftp.isConnected()) {
            channelSftp.disconnect();
        }
    }

    /**
     * 创建sftp目录
     *
     * @param channelSftp
     * @param directory
     * @return
     * @throws
     */
    public static ChannelSftp mkSftpDir(ChannelSftp channelSftp, String directory) {
        if (directory.startsWith("/")) {
            directory = directory.substring(1);
        }
        if (directory.endsWith("/")) {
            directory = directory.substring(0, directory.length() - 1);
        }
        if (StringUtils.isBlank(directory)) {
            return channelSftp; //不需要创建目录直接返回
        }
        try {
            channelSftp.cd(directory);
        } catch (Exception e) {
            try {
                channelSftp.mkdir(directory);
                channelSftp.cd(directory);
            } catch (Exception e1) {
                throw new RuntimeException(e1.getMessage());
            }
        }
        return channelSftp;
    }


}
