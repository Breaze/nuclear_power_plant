/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import exceptions.DamagedReactorException;
import exceptions.ReactorAlreadyOffException;
import exceptions.ReactorAlreadyOnException;

/**
 *
 * @author Breaze
 */
public class NuclearReactor {
    private float charge;
    private boolean isOn;
    private String status;
    
    public NuclearReactor(){
        this.isOn = false;
        this.charge = 0;
        this.status = "working";
    }
    public boolean turnOn(){
        boolean res = false;
        if(!isWorking())
            throw new DamagedReactorException("The reactor is damaged and cannot be turned on");
        if(isOn)
           throw new ReactorAlreadyOnException("The reactor is already on");
        this.isOn = true;
        res = true;
        return res;
    }
    
    public boolean turnOff(){
        boolean res = false;
        if(!isWorking())
            throw new DamagedReactorException("The reactor is damaged and cannot be turned off");
        if(!isOn)
           throw new ReactorAlreadyOffException("The reactor is already off");
        this.isOn = false;
        this.charge = 0;
        res = true;
        return res;
    }
    
    public void chargeReactor(int value){
        if(!isWorking())
            throw new DamagedReactorException("The reactor is damaged and cannot be charged");
        this.charge+=value;
        if(this.charge>100)
        {
            this.turnOff();
            this.status = "damaged";
            throw new DamagedReactorException("The reactor is damaged due the charge level surpassed the limit");
        }
    }
    
    public void dischargeReactor(int value){
        if(!isWorking())
            throw new DamagedReactorException("The reactor is damaged and cannot be discharged");
        this.charge-=value;
        if(this.charge<0)
            this.charge = 0;
    }
    
    public boolean repairReactor(){
        boolean res = false;
        if(isWorking())
            return res;
        res = true;
        this.status = "working";
        this.charge = 0;
        return res;
    }
    
    public boolean isWorking(){
        boolean isWorking = (this.status.equals("working"));
        return isWorking;
    }
    
}
