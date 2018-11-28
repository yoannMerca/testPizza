package classe;

public enum CategoriePIzzaEnum {
	FROMAGE (1,"Fromage"),
	VIANDE (2,"Viande"),
	POISSON(3,"Poisson"),
	AUTRE(4,"Autre");
	
	private String categorie;
	private int id;
	private CategoriePIzzaEnum(int id, String cat) {
		this.categorie = cat;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * 
	 * @param id
	 * @return par defaut Autre sinon ca cherche l'enum en fonction de son id
	 */
	public static CategoriePIzzaEnum getEnum(int id){
		CategoriePIzzaEnum cat = CategoriePIzzaEnum.AUTRE;
		 for(CategoriePIzzaEnum enu : values()){
			 if( enu.id == id){
				 cat = enu;
			 }
		 }
		 return cat;
	}
		
}
