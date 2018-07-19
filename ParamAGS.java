public class ParamAGS{
	public int TamanoPob; //tamano de la poblacion
	public int NoGeneraciones; //numero de generaciones (iteraciones)
	public double PCruza; //probabilidad de cruza
	public double PMuta; //probabilidad de mutacion 

	public ParamAGS(int TamanoP, int NoGeneraciones, double PCruza, double PMuta){
		this.TamanoPob = TamanoP;
		this.NoGeneraciones = NoGeneraciones;
		this.PCruza = PCruza;
		this.PMuta = PMuta;
	}
}