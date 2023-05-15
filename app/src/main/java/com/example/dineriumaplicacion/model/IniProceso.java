package com.example.dineriumaplicacion.model;

public class IniProceso {
    String objFechaPropuesta, objMontoAhorro, objPresupuesto;
    public IniProceso(){}

    public IniProceso(String objFechaPropuesta, String objMontoAhorro, String objPresupuesto) {
        this.objFechaPropuesta = objFechaPropuesta;
        this.objMontoAhorro = objMontoAhorro;
        this.objPresupuesto = objPresupuesto;
    }

    public String getObjFechaPropuesta() {
        return objFechaPropuesta;
    }

    public void setObjFechaPropuesta(String objFechaPropuesta) {
        this.objFechaPropuesta = objFechaPropuesta;
    }

    public String getObjMontoAhorro() {
        return objMontoAhorro;
    }

    public void setObjMontoAhorro(String objMontoAhorro) {
        this.objMontoAhorro = objMontoAhorro;
    }

    public String getObjPresupuesto() {
        return objPresupuesto;
    }

    public void setObjPresupuesto(String objPresupuesto) {
        this.objPresupuesto = objPresupuesto;
    }
}
