package ru.namibios.bdofishbot.config;

import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonIgnore;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class User {

    private static final Logger LOG = Logger.getLogger(User.class);

    private boolean premium;
    private Date premiumStart;
    private Date premiumEnd;

    private boolean blocked;

    private String hash;
    private String compName;
    private String name;
    private String home;
    private String encoding;
    private String os;
    private String osArch;
    private String osVersion;
    private String country;
    private String version;

    private String message;
    private Integer code;

    public void setVersion(String version) {
        this.version = version;
    }

    public User() {

        this.hash = initHash();
        this.version = initVersion();
        this.compName = initCompName();
        this.name = System.getProperty("user.name");
        this.home = System.getProperty("user.home");
        this.encoding = System.getProperty("file.encoding");
        this.os = System.getProperty("os.name");
        this.osArch = System.getProperty("os.arch");
        this.osVersion = System.getProperty("os.version");
        this.country = System.getProperty("user.country");
    }

    private String initCompName() {

        try {

            return InetAddress.getLocalHost().getHostName();

        } catch (UnknownHostException ignored) {}

        return "UNKNOWN";
    }

    private String initVersion(){

        try {

            if (version != null) {
                return version;

            } else {
                version = Files.readAllLines(Paths.get("version")).get(0);
                return version;
            }

        } catch (IOException e) {
            LOG.error(ExceptionUtils.getString(e));
        }

        return null;
    }

    public void saveHash() {

        String home = System.getProperty("user.home") + "/fishbotkey";

        try {

            if (!Files.exists(Paths.get(home))) {
                LOG.debug("hash saved..");
                Files.write(Paths.get(home), hash.getBytes());
            }

        } catch (IOException e) {
            LOG.error(ExceptionUtils.getString(e));
        }

    }

    private String initHash() {

        if (hash != null) {
            return hash;

        } else {
            String home = System.getProperty("user.home") + "/fishbotkey";
            if (Files.exists(Paths.get(home))) {
                try {
                    LOG.debug("Load hash");
                    hash = Files.readAllLines(Paths.get(home)).get(0);
                    return hash;

                } catch (IOException e) {
                    LOG.error(ExceptionUtils.getString(e));
                }
            }

        }

        return hash;
    }

    @JsonIgnore
    public boolean isWin(){
        return os.startsWith("Win");
    }

    public String getCompName() {
        return compName;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public Date getPremiumStart() {
        return premiumStart;
    }

    public Date getPremiumEnd() {
        return premiumEnd;
    }

    public boolean isPremium() {
        return premium;
    }

    public String getHash() {
        return hash;
    }

    public String getName() {
        return name;
    }

    public String getHome() {
        return home;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getOs() {
        return os;
    }

    public String getOsArch() {
        return osArch;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getCountry() {
        return country;
    }

    public String getVersion() {
        return version;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "User{" +
                "premium=" + premium +
                ", premiumStart=" + premiumStart +
                ", premiumEnd=" + premiumEnd +
                ", blocked=" + blocked +
                ", hash='" + hash + '\'' +
                ", compName='" + compName + '\'' +
                ", name='" + name + '\'' +
                ", home='" + home + '\'' +
                ", encoding='" + encoding + '\'' +
                ", os='" + os + '\'' +
                ", osArch='" + osArch + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", country='" + country + '\'' +
                ", version='" + version + '\'' +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
