package com.revature.model;

import java.util.Objects;

public class Ticket {

    private int id;
    private double amount;
    private String description;

    private int categoriesId;
    private int statusId;
    private int usersId;


    public Ticket() {
    }

    public Ticket(int id, double amount, String description, int categoriesId, int statusId, int usersId) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.categoriesId = categoriesId;
        this.statusId = statusId;
        this.usersId = usersId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", amount=" + amount +
                ", descriptionId=" + description +
                ", categoriesId=" + categoriesId +
                ", statusId=" + statusId +
                ", usersId=" + usersId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && Double.compare(ticket.amount, amount) == 0 && description == ticket.description && categoriesId == ticket.categoriesId && statusId == ticket.statusId && usersId == ticket.usersId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, description, categoriesId, statusId, usersId);
    }
}
