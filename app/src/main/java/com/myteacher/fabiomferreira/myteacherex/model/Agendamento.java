package com.myteacher.fabiomferreira.myteacherex.model;

public class Agendamento {
    Aluno aluno;
    Instrutor instrutor;
    String selectedDate;
    String selectedHour;
    String selectedPlace;

    public Agendamento(){

    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getSelectedHour() {
        return selectedHour;
    }

    public void setSelectedHour(String selectedHour) {
        this.selectedHour = selectedHour;
    }

    public String getSelectedPlace() {
        return selectedPlace;
    }

    public void setSelectedPlace(String selectedPlace) {
        this.selectedPlace = selectedPlace;
    }
}
