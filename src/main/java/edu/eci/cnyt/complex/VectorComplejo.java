package edu.eci.cnyt.complex;

import java.util.*;
public class VectorComplejo {
    private List<NumeroComplejo> elementos;

    public VectorComplejo(List<NumeroComplejo> elements){
        elementos= new ArrayList<NumeroComplejo>();
        for(NumeroComplejo c: elements){
            elementos.add(c);
        }
    }

    public VectorComplejo(){
        elementos= new ArrayList<NumeroComplejo>();
    }

    /**
     * consulta el tamano del vector
     * return
     * size
     */
    public int getSize(){
        return elementos.size();
    }

    /**
     * consulta el elemneto especificado, empieza con el elemento 0
     * return
     * elemento
     */
    public NumeroComplejo get (int index){
        return elementos.get(index);
    }

    /**
     * consulta el vector completo
     * @return
     * elementos
     */
    public List<NumeroComplejo> getElementos(){
        return this.elementos;
    }

    /**
     * anade un elemento al vector
     * param
     * elemto
     */
    public void add(NumeroComplejo c){
        elementos.add(c);
    }

    /**
     * suma el vector con otro vector
     * @param
     * vector
     * @return
     * respuesta
     */
    public VectorComplejo sumeVectores(VectorComplejo vector) throws CalculadoraComplejosException{
        if(!this.checkSize(vector)) {
            throw new CalculadoraComplejosException(CalculadoraComplejosException.SIZE_ERROR);
        }
        VectorComplejo respuesta= new VectorComplejo();
        for(int i= 0; i< this.getSize(); i++) {
            respuesta.add(this.get(i).sumeComplejo(vector.get(i)));
        }
        return respuesta;
    }

    /**
     * resta el vector con otro vector
     * @param
     * vector
     * @return
     * respuesta
     * @throws CalculadoraComplejosException
     */
    public VectorComplejo resteVectores(VectorComplejo vector) throws CalculadoraComplejosException{
        if(!this.checkSize(vector)) {
            throw new CalculadoraComplejosException(CalculadoraComplejosException.SIZE_ERROR);
        }
        VectorComplejo respuesta= new VectorComplejo();
        for(int i= 0; i< this.getSize(); i++) {
            respuesta.add(this.get(i).resteComplejo(vector.get(i)));
        }
        return respuesta;
    }

    /**
     * hace el inverso del vector
     * @return
     * respuesta
     */
    public VectorComplejo inversaVector() {
        NumeroComplejo menosUno= new NumeroComplejo(-1.0,0.0);
        return this.multiplicacionEscalar(menosUno);
    }

    /**
     * hace el producto escalar del vector con un numero complejo
     * @param
     * c
     * @return
     * respuesta
     */
    public VectorComplejo multiplicacionEscalar(NumeroComplejo c){
        VectorComplejo respuesta= new VectorComplejo();
        for(int i= 0; i< this.getSize(); i++){
            respuesta.add(this.get(i).productoComplejo(c));
        }
        return respuesta;
    }
    
    /**
     * retorna la adjunta del vector
     * @return
     * adujta
     */
    public VectorComplejo adjunta() {
    	VectorComplejo adjunta= new VectorComplejo();
    	for (int i=0; i< this.getSize();i++) {
    		adjunta.add(this.get(i).conjugado());
    	}
    	return adjunta;
    }

    /**
     * Retorna la norma del vector
     * @return Double la norma del vector
     */
    public Double norma() {
        Double ans=0.0;
        for(int i= 0; i< this.getSize(); i++) {
            ans+=Math.pow(this.get(i).getParteReal(), 2)+Math.pow(this.get(i).getParteImaginaria(), 2);
        }
        ans=Math.sqrt(ans);
        return ans;
    }

    /**
     * Retorna la distancia entre los dos vectores
     * @param otro El otro vector
     * @return Double La distancia entre los dos vectores
     * @throws CalculadoraComplejosException
     */
    public Double distancia(VectorComplejo otro) throws CalculadoraComplejosException {
        return this.resteVectores(otro).norma();
    }

    /**
     * retorna el producto interno del vector
     * @param vc el otro vector
     * @return NumeroComplejo el resultado
     * @throws CalculadoraComplejosException
     */
    public NumeroComplejo productoInterno(VectorComplejo vc) throws CalculadoraComplejosException{
        NumeroComplejo respuesta= new NumeroComplejo(0.0,0.0);
        if(!this.checkSize(vc)){
            throw new CalculadoraComplejosException(CalculadoraComplejosException.SIZE_ERROR);
        }
        for(int i= 0; i< this.getSize(); i++){
            respuesta= respuesta.sumeComplejo(this.get(i).productoComplejo(vc.get(i)));
        }
        return respuesta;
    }
    
    /**
     * retotrna el producto tensor entre vectores
     * @param vector, el vector para hacer el producto
     * @return resp, el vector que es reusltado de hacer el producto vector entre los vectores
     */
    public VectorComplejo productoTensor(VectorComplejo vector) {
    	List<NumeroComplejo> respuesta= new ArrayList<NumeroComplejo>();
    	for (int i= 0; i< this.getSize(); i++) {
    		for (int j= 0; j< vector.getSize(); j++) {
    			respuesta.add(this.get(i).productoComplejo(vector.get(j)));
    		}
    	}
    	return new VectorComplejo(respuesta);
    }


    /**
     * valida el tama??o del vector con el que se va a operar
     * @return
     * flag
     */
    private boolean checkSize(VectorComplejo vector){
        boolean flag= false;
        if(vector.getSize() == this.getSize()){
            flag= true;
        }
        return flag;
    }

    public NumeroComplejo[] getArray() {
        NumeroComplejo[] ans=new NumeroComplejo[elementos.size()];
        for(int i=0;i<elementos.size();i++){
            ans[i]=elementos.get(i);
        }
        return ans;
    }

    /**
     * pasa a string el valor del Vector
     * @return s
     */
    public  StringBuilder print(){
        StringBuilder s= new StringBuilder();
        for(int i= 0; i< this.getSize(); i++){
            s.append(this.get(i).toString());
            s.append(" ");
        }
        return s;
    }

    /**
     * pasa a String el vector
     * @return s 
     */
    public String toString() {
    	StringBuilder s= new StringBuilder();
        for(int i= 0; i< this.getSize(); i++){
            s.append(this.get(i).toString());
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o){
        VectorComplejo nv= (VectorComplejo) o;
        boolean flag= true;
        if(!this.checkSize(nv)){
            flag= false;
        }
        else{
            for(int i= 0;i< this.getSize();i++){
                if(!(this.get(i).equals(nv.get(i)))){
                    flag= false;
                    break;
                }
            }
        }
        return flag;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.elementos);
        return hash;
    }
}
