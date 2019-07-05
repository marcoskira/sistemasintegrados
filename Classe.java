//package algoritmo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.jblas.DoubleMatrix;
import java.lang.Math;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream; 
import java.awt.image.Raster; 
import java.awt.image.DataBufferByte;
import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileWriter;



public class Classe {

	public static void main(String[] args) {
		int i = 0, j = 0;

		/* Le e transpoem a matriz do modelo */
		DoubleMatrix H = leDados("H-1.txt", 50816, 3600);
		DoubleMatrix Ht = H.transpose();

		/* Le a matriz do sinal */
		DoubleMatrix g = leDados("g-1.txt", 50816);

		/* Inicializa os vetores para a resolução do sistema de equações normais */
		DoubleMatrix f = DoubleMatrix.zeros(3600);    //Vetor com a imagem
		DoubleMatrix r = DoubleMatrix.zeros(50816);   //Vetor de residuo
		DoubleMatrix r2 = DoubleMatrix.zeros(50816);  //Vetor com o novo residuo
		DoubleMatrix p = DoubleMatrix.zeros(3600);    //Correcao da imagem f

		/* r = g - Hf */
		r = g.sub(H.mmul(f));
		/* p = (H^t)r*/
		p = Ht.mmul(r);

		/* Primeira diferenca da condição de parada */
		double diff = Math.abs(r.norm2() - r2.norm2()) ;
		/* Valores escalares a serem usados */
		double alpha, beta;

		for (i = 0 ; i < 10 && diff > 0.0001 ; i++) {
			/* alpha = (r^t)r / (p^t)p */
			alpha = r.dot(r) / p.dot(p);
			
			/*f = f + alpha*p*/
			f = f.add(p.mmul(alpha));

			/*r2 = r - alpha*Hp */
			r2 = r.sub(H.mmul(p).mmul(alpha));
			
			/*beta = (r2^t)r2 / (r^t)r */
			beta = r2.dot(r2) / r.dot(r);
			
			/* p = (H^t)r2 + beta*p */
			p = Ht.mmul(r2).add(p.mmul(beta));
			
			/*diff = ||r| - |r2||*/
			diff = Math.abs(r.norm2() - r2.norm2()) ;
			
			r = r2;

		}

		/* Transpoem valores da imagem reconstruida pro intervalo [0,255]*/
		
		//Pega maximo e minimo
		double max = -1000;
		double min = 999999999;

		for (i = 0 ; i < 3600 ; i++) {
			if (Math.abs(f.get(i)) > max) 
				max = Math.abs(f.get(i));
			if (Math.abs(f.get(i)) < min)
				min = Math.abs(f.get(i));
		}

		//Faz as contas
		int[] img = new int[3600];
		int imgMax = 0;

		for (i = 0 ; i < 3600 ; i++) {
			img[i] = (int)Math.round((Math.abs(f.get(i))-min)*(255)/(max-min));
			if (img[i] > imgMax) imgMax = img[i];
		}
		
		//Escreve a imagen pgm
		
		salvaPgm (img, 60, 60, imgMax, "agoraEhPraValer.pgm");
			
		
	}

	public static void salvaPgm (int[] dados, int nRows, int nCows, int maxValue, String fileName) {
		String[] output = new String[dados.length];

		for (int i = 0 ; i < dados.length ; i++) {
			output[i] = Integer.toString(dados[i]);
		}

		File saida = new File (fileName);

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(saida));

			writer.write("P2\n");

			writer.write(Integer.toString(nRows));
			writer.write(" ");
			writer.write(Integer.toString(nCows));
			writer.write("\n");

			writer.write(Integer.toString(maxValue) + "\n");	
			for (int i = 0 ; i < dados.length ; i++){
				writer.write(output[i]);
				writer.write("\n");
			}

			writer.flush();
			writer.close();

		} catch (Exception e) {}

	}

	public static DoubleMatrix leDados (String path, int size) {
		DoubleMatrix output = new DoubleMatrix (size);
		int i =0, j = 0;

		try {
			FileReader f = new FileReader(new File(path));
			BufferedReader reader = new BufferedReader(f);
			String text = null;

			while ((text = reader.readLine()) != null) {
				output.put(i++, (Double.parseDouble(text)));
			}

			reader.close();
			f.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}

	public static DoubleMatrix leDados (String path, int nRows, int nCols) {
		DoubleMatrix output = new DoubleMatrix (nRows, nCols);		

		int i = 0, j = 0;
		try {
			FileReader f = new FileReader(new File(path));
			BufferedReader reader = new BufferedReader(f);
			String text = null;

			while ((text = reader.readLine()) != null) {
				String[] values = text.split(",",-1);
				for (String v : values) {
					output.put(i, j++, (Double.parseDouble(v)));
				}
				j = 0;
				i++;
			}

			reader.close();
			f.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return output;
	}
}
