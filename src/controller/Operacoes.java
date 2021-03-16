package controller;

import java.util.concurrent.Semaphore;

public class Operacoes extends Thread
{

	private int idThread;
	private int tempoCalc;
	private int tempoTrans;
	private Semaphore semaforo;

	public Operacoes(int idThread, Semaphore semaforo)
	{
		this.idThread = idThread;
		this.semaforo = semaforo;
	}

	@Override
	public void run()
	{
		demonstrarTipo();
		validarID();
	}


	private void validarID()
	{

		if (idThread % 3 == 1)
		{
			for (int i = 0; i < 4; i++)
			{
				tempoCalc = (int) ((Math.random() * 801) + 200);
				tempoTrans = 1000;
				realizarCalc(tempoCalc);
				try
				{
					semaforo.acquire();
					transacionar(tempoTrans);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				finally
				{
					semaforo.release();
				}				
			}
		}
		else if (idThread % 3 == 2)
		{
			for (int i = 0; i < 6; i++)
			{
				tempoCalc = (int) ((Math.random() * 1001) + 500);
				tempoTrans = 1500;
				realizarCalc(tempoCalc);
				try
				{
					semaforo.acquire();
					transacionar(tempoTrans);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				finally
				{
					semaforo.release();
				}				
			}
		}
		else if (idThread % 3 == 0)
		{
			for (int i = 0; i < 6; i++)
			{
				tempoCalc = (int) ((Math.random() * 1001) + 1000);
				tempoTrans = 1500;
				realizarCalc(tempoCalc);
				try
				{
					semaforo.acquire();
					transacionar(tempoTrans);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				finally
				{
					semaforo.release();
				}				
			}
		}
	}

	private void realizarCalc(int tempoCalc)
	{
		
		try
		{
			sleep(tempoCalc);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.print("#" + idThread);
		System.out.printf(" realizou operações durante %.2f",(tempoCalc / Math.pow(10, 3)));
		System.out.println("s.");

	}

	private void transacionar(int tempoTrans)
	{

		try
		{
			sleep(tempoTrans);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.print("#" + idThread);
		System.out.printf(" transacionou durante %.2f",(tempoTrans / Math.pow(10, 3)));
		System.out.println("s.");

	}
	
	private void demonstrarTipo()
	{
		if (idThread % 3 == 1)
		{
			System.out.println("#" + idThread + " tipo a");
		}
		else if (idThread % 3 == 2)
		{
			System.out.println("#" + idThread + " tipo b");
		}
		else if (idThread % 3 == 0)
		{
			System.out.println("#" + idThread + " tipo c");
		}
		
	}
}
