import java.io.*;
import java.util.*;
//implementacion para la clase de poblacion
public class CPoblacion{
	public ParamAGS mParamPB; //parametros de la poblacion, que pedo como usas constructores de otra clase 
	public ParamsPr mPraramPR; //parametros del problema
	public CIndividuo mPoblacion[]; //poblacion de individuos
	public ArrayList<CIndividuo> elite = new ArrayList<CIndividuo>();

	public CPoblacion(ParamAGS ParamPb, ParamsPr ParamPr){
		this.mParamPB = ParamPb;
		this.mPraramPR = ParamPr;
		this.mPoblacion = new CIndividuo[this.mParamPB.TamanoPob];
		for(int i = 0; i<this.mParamPB.TamanoPob;i++){
			this.mPoblacion[i] = new CIndividuo(ParamPr);
			//this.mejores[i] = new CIndividuo(ParamPr);
		}
	}

	public void Evaluar (){ //evalua a la poblacion
		double fitnessTotal = 0.0;
		double fitnessRelativo= 0.0;
		double acumulado = 0.0;
		//int lugares = elite.size();
		int mejorIndi = 0;
		double valorEva = 0.0; //inicializacion para comparar
		CIndividuo temp;
		for(int i = 0; i<this.mParamPB.TamanoPob; i++){
			this.mPoblacion[i].Decodifica();
			this.mPoblacion[i].EvaluarFuncion();
			fitnessTotal = fitnessTotal + this.mPoblacion[i].getEvaluacion();
		}
		for(int i = 0; i<this.mParamPB.TamanoPob; i++){
			this.mPoblacion[i].setFitness(this.mPoblacion[i].getEvaluacion()/fitnessTotal);
			acumulado = acumulado + this.mPoblacion[i].getFitness();
			this.mPoblacion[i].setAcumulado(acumulado);
		}


		for(int i = 0; i < this.mParamPB.TamanoPob; i++){
			if(this.mPoblacion[i].getEvaluacion() > valorEva){
				mejorIndi = i;
				valorEva = this.mPoblacion[i].getEvaluacion();
			}
		}
		System.out.println("pruebaaaaa");
		System.out.println(elite.size());
		if(elite.size() > 0)
			System.out.println(elite.get((elite.size())-1).getEvaluacion());
		System.out.println(this.mPoblacion[mejorIndi].getEvaluacion());
		System.out.println("aaaa");

		if(elite.size() == 0){
			System.out.println(elite.size());
			elite.add(this.mPoblacion[mejorIndi]); //agrega el mejor individuo a elite
			System.out.println(this.mPoblacion[mejorIndi]);
			System.out.println("holaaaaa");
			System.out.println(mejorIndi);
			System.out.println(elite.size());
			elite.get(0).imprimir(111);
		}else if((elite.get((elite.size())-1)).getEvaluacion() <= this.mPoblacion[mejorIndi].getEvaluacion()){
			System.out.println("dos");
			elite.add(this.mPoblacion[mejorIndi]);
			System.out.println(elite.size());
		}else{
			System.out.println("tres");
			temp = elite.get((elite.size())-1);
			elite.add(temp);
			int m = (int)(Math.random()*this.mParamPB.TamanoPob);
			System.out.println(m);
			this.mPoblacion[m] = temp;
			System.out.println(elite.size());
		}
	}

	public void Seleccion(){ //selecciona por metodo de ruleta
		double aleatorio;
		int j = 0;
		CIndividuo poblacionAux[] = new CIndividuo[this.mParamPB.TamanoPob]; //un arrgelo del tamano con las car
		for(int i = 0; i<this.mParamPB.TamanoPob; i++){
			aleatorio = Math.random();
			//revisaaaaar
			while(this.mPoblacion[j].getAcumulado()<=aleatorio && j < this.mParamPB.TamanoPob){ //lo va a hacer hasta que llegue a uno, puedo tamr más de uno del mismo 
				j++; //cambia de inice que se usa para la de abajo
			}
			poblacionAux[i] = this.mPoblacion[j]; //mete dentro de la poblacion auxiliar los seleccionados
		}
		for(int i = 0; i<this.mParamPB.TamanoPob; i++)
			this.mPoblacion[i] = poblacionAux[i]; //remplaza los viejos por los seleccionados
	}

	public void opeCruza(CIndividuo padreA, CIndividuo padreB){ //operacion de cruza 
		int Cromosoma[]  = new int[this.mPraramPR.TamanoIndividuo];
		int ptocorte = (int)(Math.random()*this.mPraramPR.TamanoIndividuo);
		for(int i = ptocorte;i<this.mPraramPR.TamanoIndividuo;i++){ //no se toma el <= porque son indices 
			padreA.setGene(i,padreB.getGene(i));                    //de arreglos
			padreB.setGene(i,padreA.getGene(i));
		}
	}

	public void opeMutacion(CIndividuo iAMutar){ //operacion de mutacion
		int i = 0;
		for(i = 0; i < this.mPraramPR.TamanoIndividuo; i++){
			if(Math.random() <= this.mParamPB.PMuta){
				if(iAMutar.getGene(i) == 1) 
					iAMutar.setGene(i,0);
				else
					iAMutar.setGene(i,1);
			}
		}
	}

	public void ImprimirPob(){
		for(int i = 0; i < this.mParamPB.TamanoPob; i++){
			this.mPoblacion[i].imprimir(i);
		}
	}

	public void Cruzar () { //realiza como tal el proceso de cruza
		double aleatorio;
		boolean pareja = false;
		boolean padreA = false;
		boolean padreB = false;
		CIndividuo PadreA = new CIndividuo(this.mPraramPR);
		CIndividuo PadreB = new CIndividuo(this.mPraramPR);
		for(int i = 0; i < this.mParamPB.TamanoPob; i++){
			aleatorio = Math.random();
			if(aleatorio <= this.mParamPB.PCruza){
				if(!padreA){
					PadreA = this.mPoblacion[i];
					padreA = true;
				}else{  //a fuerza si entra al else va a tener ya una pareja 
					PadreB = this.mPoblacion[i];
					padreB = true;
					pareja = true;
				}
				if(pareja){ //ahuevo va a entrar a pareja cuando ya tenga dos individuos
					this.opeCruza(PadreA,PadreB);
					pareja = false;
					padreA = false; //los vuelve a poner en flase para la siguiente pareja
					padreB = false;
				}
			}
		}
	}

	public void Mutar(){ //realiza el proceso de mutacion
		for(int i = 0; i < this.mParamPB.TamanoPob; i++)
			this.opeMutacion(this.mPoblacion[i]);   //solo manda al individuo para la funcion
	}

	public void MejorIndividuo(){
		int n = elite.size();
		elite.get(n-1).imprimir(1);
		System.out.println("renew");
	}

	public void impEli(){
		for(int i = 0; i < elite.size(); i++){
			elite.get(i).imprimir(i+1);
		}
		elite.get(0).imprimir(0);
	}

	public void OrdenarDatos(){
		int i,j;
		CIndividuo indAux;
		for(i = this.mParamPB.TamanoPob-2; i >= 1; i--){
			for(j = 0; j <= 1; j++){
				double evaluacionA = this.mPoblacion[j].getEvaluacion();
				double evaluacionB = this.mPoblacion[j+1].getEvaluacion();
				if(evaluacionA > evaluacionB){
					indAux = this.mPoblacion[j];
					this.mPoblacion[j] = this.mPoblacion[j+1];
					this.mPoblacion[j+1] = indAux;
				}
			}
		}

	}
}