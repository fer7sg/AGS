import java.io.*;
public class AGS{
	public static void main(String[] args) throws IOException{
		ParamAGS ParametrosAGS = new ParamAGS(5, 5, 0.90, 0.015);
		// Parametros del problema
		ParamsPr ParametrosPR = new ParamsPr(2, 33);
		ParametrosPR.TamanoVariables[0] = 18;
		ParametrosPR.TamanoVariables[1] = 15;
		ParametrosPR.LimVariables[0][0] = -3.0;
		ParametrosPR.LimVariables[0][1] = 12.1;
		ParametrosPR.LimVariables[1][0] = 4.1;
		ParametrosPR.LimVariables[1][1] = 5.8;
		// Se crea un objeto AGSimple
		CAGSimple agssimple = new CAGSimple(ParametrosAGS,ParametrosPR);
		// Mostrar los datos con que trabaja el AG simple
		agssimple.ImprimeParametros();
		// Evolucionar
		agssimple.Evoluciona();
		// Fin del AGS
		System.out.print("\n\nEl AGS termino el proceso.\n");
	}
}