import java.io.*;
import java.text.*;
//Implementacion...?
public class CAGSimple{
	private ParamAGS mParamPob; //Parametros de la poblacion
	private ParamsPr mParamPro; //parametros para el problema
	private CPoblacion mPoblacion; //clase poblacion??
 
 	public CAGSimple(ParamAGS ParamPob,ParamsPr ParamPro){
		this.mParamPob = ParamPob;
		this.mParamPro = ParamPro;
		}

	public void ImprimeParametros(){ //Muestra en pantalla los datos con que trabaja el AG
		// Obejeto para el formato de numeros decimales
		NumberFormat enteros = NumberFormat.getNumberInstance();
		NumberFormat decimal = NumberFormat.getNumberInstance();
		enteros.setMaximumFractionDigits(0);
		enteros.setMinimumIntegerDigits(2);
		decimal.setMaximumFractionDigits(4);
		decimal.setMaximumIntegerDigits(2);
		String salida;
		// Salida por pantalla
		System.out.print("\nDATOS CON QUE TRABAJA EL AGSimple");
		salida = "\nNumero de individuos :"+enteros.format(this.mParamPob.TamanoPob);
		System.out.print(salida);
		salida = "\nNumero de generaciones :"+enteros.format(this.mParamPob.NoGeneraciones);
		System.out.print(salida);
		salida = "\nProbabilidad de cruza :"+decimal.format(this.mParamPob.PCruza);
		System.out.print(salida);
		salida = "\nProbabilidad de mutacion :"+decimal.format(this.mParamPob.PMuta);
		System.out.print(salida);
		salida = "\nTamano del individuo :"+enteros.format(this.mParamPro.TamanoIndividuo);
		System.out.print(salida);
		salida = "\nNumero de variables :"+enteros.format(this.mParamPro.NoVariables);
		System.out.print(salida);
		System.out.print("\nTamano de variables :");
		salida = "\n x="+enteros.format(this.mParamPro.TamanoVariables[0]);
		System.out.print(salida);
		salida = "\n y="+enteros.format(this.mParamPro.TamanoVariables[1]);
		System.out.print(salida);
		System.out.print("\nLimites de variables :");
		salida = "\n xi="+decimal.format(this.mParamPro.LimVariables[0][0])+"\txs="+decimal.format(this.mParamPro.LimVariables[0][1]);
		System.out.print(salida);
		salida = "\n yi="+decimal.format(this.mParamPro.LimVariables[1][0])+"\tys="+decimal.format(this.mParamPro.LimVariables[1][1])+"\n";
		System.out.print(salida);
	}
	
	public void Evoluciona() throws IOException{ //Inicia los ciclos de evolucion
		InputStreamReader flujo = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(flujo);
		// GENERAR POBLACION INICIAL
		System.out.print("\n==================================================\n");
		System.out.print("\nGenerando la poblacion inicial.............");
		this.mPoblacion = new CPoblacion(this.mParamPob,this.mParamPro);
		//System.out.println(mParamPob.TamanoPob);
		//System.out.println(mPoblacion.mParamPB);
		System.out.print("[LISTO]");
		// EVALUAR POBLACION INICIAL
		System.out.print("\nEvaluando poblacion inicial................");
		this.mPoblacion.Evaluar();
		System.out.print("[LISTO]\n");
		System.out.print("\nImprimir la poblacion inicial [S|N] ? : ");
		String opcion = teclado.readLine();
		if(opcion.equalsIgnoreCase("s")) 
			this.mPoblacion.ImprimirPob();
		System.out.print("\nCICLOS DE EVOLUCION");
		System.out.print("\nMotrar mejor individuo de cada generacion [S|N]? : ");
		opcion = teclado.readLine();
		// ORDENAR DATOS
		//(this->m_aptPoblacion)->OrdenarDatos();
		int generacion = 0;
		do{
		System.out.print("\n==================================================");
		System.out.print("\nG E N E R A C I O N ["+(generacion+1)+"]");
		// SELECCION
		System.out.print("\nSeleccion..................................");
		this.mPoblacion.Seleccion();
		System.out.print("[LISTO]");
		// CRUZA
		System.out.print("\nCruza......................................");
		this.mPoblacion.Cruzar();
		System.out.print("[LISTO]");
		// MUTACION
		System.out.print("\nMutacion...................................");
		this.mPoblacion.Mutar();
		System.out.print("[LISTO]");
		// EVALUAR NUEVA POBLACION
		System.out.print("\nEvaluar poblacion..........................");
		this.mPoblacion.Evaluar();
		//(*this).m_aptPoblacion->ImprimirPob();
		System.out.print("[LISTO]\n");
		//(*this).m_aptPoblacion->ImprimirPob();
		// Imprimir mejor individuo?
		if(opcion.equalsIgnoreCase("s")){
			System.out.print("\n<<<<< MEJOR INDIVIDUO >>>>>");
		this.mPoblacion.MejorIndividuo();
		}
		// ORDENAR DATOS
		//(this->m_aptPoblacion)->OrdenarDatos();
		generacion++;
		}while(generacion<this.mParamPob.NoGeneraciones);
		System.out.print("\nMotrar elite [S|N]? : ");
		opcion = teclado.readLine();
		if(opcion.equalsIgnoreCase("s")){
			System.out.print("\n<<<<< Elite >>>>>");
			this.mPoblacion.impEli();
		}
	}
}
