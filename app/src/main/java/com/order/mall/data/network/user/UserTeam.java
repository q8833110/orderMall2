package com.order.mall.data.network.user;

import java.util.List;

public class UserTeam {

    /**
     * level : 0
     * totalChildUser : 2
     * totalAchievement : 839
     * userVo : [{"level":1,"account":"18780106593","registerDate":"2019-05-03T15:33:34.000+0800"}]
     */

    private int level;
    private int totalChildUser;
    private int totalAchievement;
    private List<UserVoBean> userVo;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTotalChildUser() {
        return totalChildUser;
    }

    public void setTotalChildUser(int totalChildUser) {
        this.totalChildUser = totalChildUser;
    }

    public int getTotalAchievement() {
        return totalAchievement;
    }

    public void setTotalAchievement(int totalAchievement) {
        this.totalAchievement = totalAchievement;
    }

    public List<UserVoBean> getUserVo() {
        return userVo;
    }

    public void setUserVo(List<UserVoBean> userVo) {
        this.userVo = userVo;
    }

    public static class UserVoBean {
        /**
         * level : 1
         * account : 18780106593
         * registerDate : 2019-05-03T15:33:34.000+0800
         */

        private int level;
        private String account;
        private String registerDate;

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
        }
    }
}
