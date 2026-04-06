package org.example.entities;

public class EquipeUser {
    private int equipeId;
    private int userId;

    public EquipeUser() {
    }

    public EquipeUser(int equipeId, int userId) {
        this.equipeId = equipeId;
        this.userId = userId;
    }

    public int getEquipeId() {
        return equipeId;
    }

    public void setEquipeId(int equipeId) {
        this.equipeId = equipeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "EquipeUser{" +
                "equipeId=" + equipeId +
                ", userId=" + userId +
                '}';
    }
}
