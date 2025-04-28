package sv.global.colas.utils;

public enum Estados {
	
	e1(1,"En espera"),
	e2(2,"Llamando"),
	e3(3,"En atención"),
	e4(4,"Finalizado"),
	e5(5,"Llamado no atendido"),
	e6(6,"Pendiente"),
	e7(7,"Vencido");
	private final int id;
	private final String estados;
	
	Estados(int id,String estados){
		this.id = id;
		this.estados = estados;
	}
	
	public int getId() {
		return id;
	}

	public String getEstados() {
		return estados;
	}
	
	
	public static String getEstado(Integer val){
		
		for(Estados e : Estados.values()){
			if(e.getId()==val){
				return e.getEstados();
			}
		}
		return "Inactivo";
	}

}
