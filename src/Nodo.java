/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author ivanw
 */
public class Nodo {
        public int [][]matriz;
	private Nodo padre;
	private ArrayList<Nodo> hijos;
	public boolean tipo;
	private int valor;
	
	public Nodo(int [][]matriz,boolean tipo) {
		this.padre=null;
		this.matriz=Nodo.copia(matriz);
		//true para max=1 |||| false para min=2
		this.tipo=tipo;
		this.valor=0;
		this.hijos=null;
	}
		
	/*METODOS DE CLASE*/
	
	
	

	//COPIA DE MATRIZES
	static int[][] copia(int [][] matriz) {
		int[][] copia=new int[6][7];
		for(int i=0;i<matriz.length;i++){
			for(int j=0;j<matriz[i].length;j++){
				copia[i][j]=matriz[i][j];
			}
		}
		return copia;
	}
	
	
	
	//GENERADOR DE HIJOS POR NODO
	public ArrayList<Nodo> genHijos(){
		ArrayList<Nodo> hijos=new ArrayList<Nodo>();
		int tirada=(this.tipo)?1:2;
		for(int j=0;j<this.matriz[0].length;j++) {
			for(int i=0;i<this.matriz.length;i++){
					if(i+1==this.matriz.length&&this.matriz[i][j]==0) {
						int [][]hijo=Nodo.copia(this.matriz);
						hijo[i][j]=tirada;
						Nodo ad=new Nodo(hijo,!this.tipo);
						hijos.add(ad);
					}
					if(this.matriz[i][j]!=0&&i-1>=0) {
						if(this.matriz[i-1][j]==0) {
							int [][]hijo=Nodo.copia(this.matriz);
							hijo[i-1][j]=tirada;
							Nodo ad=new Nodo(hijo,!this.tipo);
							hijos.add(ad);
						}
					}
					
			}
		}
		return hijos;
	}
	
	public int valuacionfil(){
            int p,resultado=0;
            boolean flag=false;
            if (this.tipo==true)
                  p=1;
            else
                p=2;
            int[][] copia=Nodo.copia(this.matriz);
            for(int i=0;i<copia.length;i++){
                for(int j=0;j<4;j++){
                    int cont=0;
                    for(int z=0;z<4;z++){
                        if(copia[i][j+z]==p)
                            cont++;
                        else if(copia[i][j+z]!=0){
                            cont=0;
                            break;
                        }
                    }
                    if(cont==4)
                    {
                        return 1000000;
                    }
                    if(cont>=3){
                            flag=true;
                    }
                }
                if(flag){
                    resultado++;
                    flag=false;
                }
            }
            return resultado;
        }
        
        public int valuacioncol(){
            int p,res=0;
            boolean flag=false;
            if (this.tipo==true)
                  p=1;
            else
                p=2;
            int[][] copia=Nodo.copia(this.matriz);
            for(int i=0;i<7;i++){
                for(int j=0;j<3;j++){
                    int cont=0;
                    for(int k=0;k<4;k++){
                        if(copia[j+k][i]==p)
                            cont++;
                        else if(copia[j+k][i]!=0){
                            cont=0;
                            break;
                        }
                        if(cont==4)
                        {
                            return 1000000;
                        }
                        if(cont>=3){
                            flag=true;
                        }
                    }
                }
                if(flag){
                    res++;
                    flag=false;
                }
            }
            return res;
        }
        /*valuacion de la diagonal positiva*/
        public int valdiagpos(){
             int p,res=0;
            boolean flag=false;
            if (this.tipo==true)
                  p=1;
            else
                p=2;
            int[][] copia=Nodo.copia(this.matriz);
            int cont=0;
            for(int i=0;i<3;i++){
                cont=0;
                for(int j=0;j<4;j++){
                    if(copia[i+j][i+j]==p){
                       cont++;
                    }
                    else if(copia[i+j][i+j]!=0){
                        cont=0; 
                        break;
                    }
                    if(cont==4)
                    {
                        return 1000000;
                    }
                    if(cont>=3){
                        flag=true;
                    }
                }
            }
            if(flag){
                    res++;
                    flag=false;
            }
            /*2 caso*/
            for(int i=0;i<3;i++){
                cont=0;
                for(int j=0;j<4;j++){
                    if(copia[i+j][i+1+j]==p){
                       cont++;
                    }
                    else if(copia[i+j][i+1+j]!=0){
                        cont=0; 
                        break;
                    }
                    if(cont==4)
                    {
                        return 1000000;
                    }
                    if(cont>=3){
                        flag=true;
                    }
                }
            }
            if(flag){
                    res++;
                    flag=false;
            }
            /*Caso 3*/
            for(int i=0;i<2;i++){
                cont=0;
                for(int j=0;j<4;j++){
                    if(copia[i+j][i+2+j]==p){
                       cont++;
                    }
                    else if(copia[i+j][i+2+j]!=0){
                        cont=0; 
                        break;
                    }
                    if(cont==4)
                    {
                        return 1000000;
                    }
                    if(cont>=3){
                        flag=true;
                    }
                }
            }
            if(flag){
                    res++;
                    flag=false;
            }
            /*caso 4*/
            for(int i=0;i<1;i++){
                cont=0;
                for(int j=0;j<4;j++){
                    if(copia[i+j][i+3+j]==p){
                       cont++;
                    }
                    else if(copia[i+j][i+3+j]!=0){
                        cont=0; 
                        break;
                    }
                    if(cont==4)
                    {
                        return 1000000;
                    }
                    if(cont>=3){
                        flag=true;
                    }
                }
            }
            if(flag){
                    res++;
                    flag=false;
            }
            /*caso 5*/
            for(int i=0;i<2;i++){
                cont=0;
                for(int j=0;j<4;j++){
                    if(copia[i+j+1][i+j]==p){
                       cont++;
                    }
                    else if(copia[i+j+1][i+j]!=0){
                        cont=0; 
                        break;
                    }
                    if(cont==4)
                    {
                        return 1000000;
                    }
                    if(cont>=3){
                        flag=true;
                    }
                }
            }
            if(flag){
                    res++;
                    flag=false;
            }
            /*caso 6*/
            for(int i=0;i<1;i++){
                cont=0;
                for(int j=0;j<4;j++){
                    if(copia[i+j+2][i+j]==p){
                       cont++;
                    }
                    else if(copia[i+j+2][i+j]!=0){
                        cont=0; 
                        break;
                    }
                    if(cont==4)
                    {
                        return 1000000;
                    }
                    if(cont>=3){
                        flag=true;
                    }
                }
            }
            if(flag){
                    res++;
                    flag=false;
            }
            return res;
        }
	
        /*diagonal negativa*/
        public int valdiagneg(){
            int res=0;
            int[][] copia=Nodo.copia(this.matriz);
            int[][] nueva=new int[6][7];
            for(int i=0;i<6;i++){
                nueva[i]=Nodo.reverse(copia[i],7);
            }
            Nodo reverso=new Nodo(nueva,this.tipo);
            res=reverso.valdiagpos();
            return res;
        }
        
        public int posiblesfil(){
            int p,res=0;
            if (this.tipo==true)
                  p=1;
            else
                p=2;
            int[][] copia=Nodo.copia(this.matriz);
            for(int i=0;i<6;i++){
                for(int j=0;j<4;j++){
                    int cont=0;
                    for(int k=0;k<4;k++){
                        if(copia[i][j+k]==p||copia[i][j+k]==0){
                            cont++;
                        }else
                            cont=0;
                    }
                    if(cont==4){
                        res++;
                    }
                }
            }
            return res;
        }
        
        public int posiblescol(){
            int p,res=0;
            if (this.tipo==true)
                  p=1;
            else
                p=2;
            int[][] copia=Nodo.copia(this.matriz);
            for(int i=0;i<7;i++){
                for(int j=0;j<3;j++){
                    int cont=0;
                    for(int k=0;k<4;k++){
                        if(copia[j+k][i]==p||copia[j+k][i]==0){
                            cont++;
                        }else
                            cont=0;
                    }
                    if(cont==4){
                        res++;
                    }
                }
            }
            return res;
        }
        
        public int posiblesdiagpost(){
            int p,res=0;
            if (this.tipo==true)
                  p=1;
            else
                p=2;
            int[][] copia=Nodo.copia(this.matriz);
            int cont=0;
            for(int i=0;i<3;i++){
                cont=0;
                for(int j=0;j<4;j++){
                    if(copia[i+j][i+j]==p||copia[i+j][i+j]==0)
                       cont++;
                    else
                        cont=0; 
                }
                if(cont==4)
                    res++;      
            }/*caso 2*/
            for(int i=0;i<3;i++){
                cont=0;
                for(int j=0;j<4;j++){
                    if(copia[i+j][i+1+j]==p||copia[i+j][i+1+j]==0)
                       cont++;
                    else
                        cont=0;
                }
                 if(cont==4)
                        res++;
            }
            /*caso 3*/
            for(int i=0;i<2;i++){
                cont=0;
                for(int j=0;j<4;j++){
                    if(copia[i+j][i+2+j]==p||copia[i+j][i+2+j]==0)
                       cont++;
                    else 
                        cont=0;
                }
                if(cont==4)
                    res++;
            }
            /*caso 4*/
            for(int i=0;i<1;i++){
                cont=0;
                for(int j=0;j<4;j++){
                    if(copia[i+j][i+3+j]==p||copia[i+j][i+3+j]==0){
                       cont++;
                    }
                    else 
                        cont=0;
                }
                if(cont==4)
                    res++;
            }
            /*caso 5*/
            for(int i=0;i<2;i++){
                cont=0;
                for(int j=0;j<4;j++){
                    if(copia[i+j+1][i+j]==p||copia[i+j+1][i+j]==0)
                       cont++;
                    else 
                        cont=0;
                    
                }
                if(cont==4)
                    res++;
            }
            
            /*caso 6*/
            for(int i=0;i<1;i++){
                cont=0;
                for(int j=0;j<4;j++){
                    if(copia[i+j+2][i+j]==p||copia[i+j+2][i+j]==0)
                       cont++;
                    else
                        cont=0;   
                }
                if(cont==4)
                    res++;
            }
            
            return res;
        }
        
        public int posiblesdiagneg(){
            int res=0;
            int[][] copia=Nodo.copia(this.matriz);
            int[][] nueva=new int[6][7];
            for(int i=0;i<6;i++){
                nueva[i]=Nodo.reverse(copia[i],7);
            }
            Nodo reverso=new Nodo(nueva,this.tipo);
            res=reverso.posiblesdiagpost();
            return res;
        }
        
        public int valuacion(){
            boolean saveType=this.tipo;
            int valuacion=0;
            int valuacion2=0;
            this.tipo=true;
            valuacion+=this.posiblesfil();
            valuacion=valuacion+this.posiblescol();
            valuacion=valuacion+this.posiblesdiagneg();
            valuacion=valuacion+this.posiblesdiagpost();
            valuacion=valuacion+this.valuacioncol()*3;
            valuacion=valuacion+this.valuacionfil()*3;
            valuacion=valuacion+this.valdiagpos()*3;
            valuacion=valuacion+this.valdiagneg()*3;
            this.tipo=false;
            valuacion2+=this.posiblesfil();
            valuacion2=valuacion2+this.posiblescol();
            valuacion2=valuacion2+this.posiblesdiagneg();
            valuacion2=valuacion2+this.posiblesdiagpost();
            valuacion2=valuacion2+this.valuacioncol()*3;
            valuacion2=valuacion2+this.valuacionfil()*3;
            valuacion2=valuacion2+this.valdiagpos()*3;
            valuacion2=valuacion2+this.valdiagneg()*3;
            this.tipo=saveType;
            //System.out.println(valuacion);
            if(valuacion > 1000000){
                valuacion=1000000;
            }if(valuacion2 < -1000000){
                valuacion2=-1000000;
            }
            return valuacion-valuacion2;
        }
        
        /*metodo de reversa*/
	static int[] reverse(int a[], int n) 
        { 
            int[] b = new int[n]; 
            int j = n; 
            for (int i = 0; i < n; i++) { 
                b[j - 1] = a[i]; 
                j = j - 1; 
            }
            return b;
        }
	
        
	//gets and sets
	public Nodo getPadre() {
		return padre;
	}
	public void setPadre(Nodo padre) {
		this.padre = padre;
	}
	public ArrayList<Nodo> getHijos() {
		return hijos;
	}
	public void setHijos(ArrayList<Nodo> hijos) {
		this.hijos = hijos;
	}
	public boolean getTipo() {
		return tipo;
	}
	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}

	public int[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(int[][] matriz) {
		this.matriz = matriz;
	}
	
	
	@Override
	public String toString() {
		String cad="";
		for(int[] fila:this.matriz){
			for(int celda:fila) {
				cad=cad+" "+celda;
			}
			cad=cad+"\n";
		}
		return cad;
	}
}
