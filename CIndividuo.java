import java.math.*;
import java.text.*;
import java.io.*; 	
public class CIndividuo{
	private int mCromosoma[]; //cadena de bits de los cromosomas
	public ParamsPr mParamPr; //parametros del problema
	private double mValVars[]; //valores de las variales??
	private double mEvaluacion; //valor de evaluacion de variables
	private double mFitness; //valor de aptitud
	private double mAcumulado; //valor fitness relativo del acumulado

	public CIndividuo (ParamsPr ParamPR){ //constructor de clase
		this.mParamPr = ParamPR;
		this.mValVars = new double[this.mParamPr.NoVariables]; //almacena los valores de las variables
		this.mCromosoma = new int[this.mParamPr.TamanoIndividuo];
		for(int i=0;i<this.mParamPr.TamanoIndividuo;i++)
			this.mCromosoma[i] = (int)((Math.random()*100)%2);
	}

	public void Decodifica(){ //decodifica las variables
		int bitActual = 0;
		for(int i = 0; i < this.mParamPr.NoVariables; i++){
			int decimal = 0;
			int contador = (this.mParamPr.TamanoVariables[i])-1;
			for(int j = 0; j < this.mParamPr.TamanoVariables[i]; j++){
				if(this.mCromosoma[bitActual++] == 1)
					decimal = decimal + (int)(Math.pow(2,contador));
				contador--;
			}
			double lInf = this.mParamPr.LimVariables[i][0];
			double lSup = this.mParamPr.LimVariables[i][1];
			contador = this.mParamPr.TamanoVariables[i];
			this.mValVars[i] = lInf + ((double)decimal)*((lSup - lInf)/(Math.pow(2,contador)-1.0));
		}
	}

	public double EvaluarFuncion(){
		final double PI = 3.141592654;
		double x = this.mValVars[0];
		double y = this.mValVars[1];
		return this.mEvaluacion = 21.5 + x*(Math.sin(4*PI*x))+y*(Math.sin(20*PI*y));
	}

	public void imprimir(int i){ //ne la informacion del individuo
		NumberFormat formato = NumberFormat.getNumberInstance(); //objeto para el formato de numeros decimales
		String salida = "\nValor de las variables : x: "+formato.format(this.mValVars[0])+", y: "+formato.format(this.mValVars[1]);
		System.out.print(salida);
		formato.setMaximumFractionDigits(4);
		formato.setMinimumIntegerDigits(2);
		System.out.print("\nINFORMACION PARA EL INDIVIDUO "+i+"\n");
		System.out.print("\nCROMOSOMA: ");
		for(int j=0;j<this.mParamPr.TamanoIndividuo;j++)
			System.out.print(this.mCromosoma[j]);
		salida = "\nEvaluacion : "+formato.format(this.mEvaluacion);
		System.out.print(salida);
		salida = "\nFitness : "+formato.format(this.mFitness);
		System.out.print(salida);
		salida = "\nAcumulado : "+formato.format(this.mAcumulado);
		System.out.print(salida);
		System.out.print("\n--------------------------------------------------\n\n");
		}

	public void setGene(int gene, int valor){  //metood set para establecer un bit en el cromosoma
		mCromosoma[gene] = valor;
	}

	public int getGene(int gene){ //obtiene un bit del cromosoma
		return mCromosoma[gene];
	}

	public double getEvaluacion(){ //obtiene el valor de f
		return mEvaluacion;
	}

	public void setFitness(double fitness){
		mFitness = fitness;
	}

	public double getFitness(){ //devulve el fitness
		return mFitness;
	}

	public void setAcumulado(double acumulado){ //establece el fitness acumulado
		mAcumulado = acumulado;
	}

	public double getAcumulado(){ //devuelve el fitness acumulado
		return mAcumulado;
	}

}