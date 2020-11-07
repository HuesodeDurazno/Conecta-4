
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


import java.util.ArrayList;

/**
 *
 * @author ivanw
 */
public class Arbol {
    Nodo inicio;
	
	public Arbol(){
		inicio=null;
		
	}
	
	public Arbol(Nodo inicio) {
		this.inicio=inicio;
		this.inicio.setPadre(null);
	}
	
	public void genArbol(){
		int cont=0;
		ArrayList<Nodo> guardado=new ArrayList<Nodo>();
		guardado.add(this.inicio);
		recArb(guardado,cont);
	}
	
	private void recArb(ArrayList <Nodo> guardado,int cont){
                    ArrayList<Nodo> aux2 = null;
                    if(cont<4) {
			ArrayList<Nodo> paso=new ArrayList<Nodo>();
			for(int i=0;i<guardado.size();i++) {
                            ArrayList<Nodo> aux=guardado.get(i).genHijos();
                            if(aux.size()!=0){
                               guardado.get(i).setHijos(aux);
                               aux2= new ArrayList<Nodo>();
                               for(Nodo n: aux){
                                    if(Math.abs(n.valuacion())<1000){
                                        aux2.add(n);
                                    }
                                }
				paso.addAll(aux2);
                            }
			}
			this.recArb(paso, cont+1);
		}
		else
			System.out.println("terminado con exito");
	}
        
        public Nodo poda(){
            int maxval=-1000000000,actual;
            int alpha=-1000000000;
            int beta=1000000000;
            Nodo paso=this.inicio,aux=null;
            paso.tipo = true;
            //int ward=this.alphabeta(paso, alpha, beta);
            for(Nodo N:paso.getHijos()){
                actual=this.alphabetaPeroMejor(N, alpha, beta);
                System.out.println(actual);
                if(N.valuacion()>=1000){
                    //System.out.println("yeah!");
                    aux=N;
                    return aux;
                }
                if(actual>maxval){
                    maxval=actual;
                    aux=N;
                }
            }
            //Nodo retorno=;
            //System.out.println(aux.valuacion());
            return aux;
        }
        
        public int alphabeta(Nodo paso,int alpha,int beta){
            if(paso.getHijos()==null)
                return paso.valuacion();
            else{
                if(paso.getTipo()){
                    for(Nodo N:paso.getHijos()){
                        int guardado=alphabeta(N,alpha,beta);
                        if(guardado>alpha)
                            alpha=guardado;
                        if(alpha>=beta)
                            return beta;     
                    }
                    return alpha;
                }else{
                   for(Nodo N:paso.getHijos()){
                       int guardado=alphabeta(N,alpha,beta);
                       if(guardado<beta)
                           beta=guardado;
                       if(alpha>=beta)
                           return alpha;
                   }
                   return beta;
                }
            }
        
        }
        public int alphabetaPeroMejor(Nodo paso,int alpha,int beta){
            //paso.tipo=true;
            int v = paso.valuacion();
            //System.out.print("paso: ");
            //System.out.println(v);
            if(Math.abs(v)>=10000 || paso.getHijos()==null)
                return v;
            else{
                if(paso.getTipo()){
                    int maxEval = -1000000000;
                    for(Nodo N:paso.getHijos()){
                        int guardado=alphabetaPeroMejor(N,alpha,beta);
                        if(maxEval<guardado){
                            maxEval=guardado;
                        }
                        if(guardado>alpha)
                            alpha=guardado;
                        if(alpha>=beta)
                            break;     
                    }
                    return maxEval;
                }else{
                   int minEval = 1000000000;
                   for(Nodo N:paso.getHijos()){
                       int guardado=alphabetaPeroMejor(N,alpha,beta);
                       if(minEval>guardado){
                            minEval=guardado;
                        }
                       if(guardado<beta)
                           beta=guardado;
                       if(alpha>=beta)
                           break;
                   }
                   return minEval;
                }
            }
        
        }
        
        
        public void impresion(){
            int cont=0;
            Nodo aux=this.inicio;
            while(aux!=null){
                //System.out.println(cont++);
                if(aux.getHijos()!=null)
                    aux=aux.getHijos().get(0);
                else
                    aux=null;
            }
      } 
}
