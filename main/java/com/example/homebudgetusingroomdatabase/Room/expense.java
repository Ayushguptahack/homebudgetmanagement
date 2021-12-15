package com.example.homebudgetusingroomdatabase.Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class expense { //  class  for  storing  expense objects


    @PrimaryKey(autoGenerate = true)
    int userid;

    public String description;
    public String value;

    public expense(){

    }

    public expense(String desc, String val){
        this.description = desc;
        this.value = val;
    }

    public String  getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String  getValue(){
        return value;
    }

    public void setValue(String value){
        this.description = value;
    }
}
