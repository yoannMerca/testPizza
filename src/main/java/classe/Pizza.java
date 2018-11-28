package classe;

	public class Pizza {
		/*Id (ou identifiant unique pour chaque Pizza) : 1
			Code : MAR
			Désignation : MARGARITA
			Prix : 9,20 €
		*/
		private int idPizza;
		private String code = null;
		private String designation = null;
		private Double price = null;
		static int nbr =0;
		private CategoriePIzzaEnum categorie;
		
		/**
		 * 
		 * @param code de la pizza (les trois premieres lettre en Maj)
		 * @param designation Nom de la pizza
		 * @param price prix de la pizza
		 */
		public Pizza(String code, String designation, Double price, CategoriePIzzaEnum cat) {
			this.idPizza = nbr++;
			this.code = code;
			this.designation = designation;
			this.price = price;
			this.categorie = cat;
		}
		public Pizza(Pizza piz) {
			this.idPizza = nbr++;
			this.code = piz.getCode();
			this.designation = piz.getDesignation();
			this.price = piz.getPrice();
			this.categorie = piz.getCategorie();
		}
		/**
		 * 
		 * @return retourne le code de la pizza
		 */
		public String getCode() {
			return code;
		}
		/**
		 * 
		 * @param code modifi le code de la pizza
		 */
		public void setCode(String code) {
			this.code = code;
		}
		/**
		 * 
		 * @return retourne la designation
		 */
		public String getDesignation() {
			return designation;
		}
		/**
		 * 
		 * @param designation modifi la designation
		 */
		public void setDesignation(String designation) {
			this.designation = designation;
		}
		/**
		 * 
		 * @return retourne le prix de la pizza
		 */
		public Double getPrice() {
			return price;
		}
		/**
		 * 
		 * @param price modification du prix
		 */
		public void setPrice(Double price) {
			this.price = price;
		}
		public int getIdPizza() {
			return idPizza;
		}
		
		
		
		public CategoriePIzzaEnum getCategorie() {
			return categorie;
		}
		public void setCategorie(CategoriePIzzaEnum cat) {
			this.categorie = cat;
		}
		/**
		 * 
		 * @return methode pour afficher le menu de l'application
		 */
		
		public static String displayMenu() {
			return "***** Pizzeria Administration *****\r\n" + 
					"1. Lister les pizzas\r\n" + 
					"2. Ajouter une nouvelle pizza\r\n" + 
					"3. Mettre à jour une pizza\r\n" + 
					"4. Supprimer une pizza\r\n" + 
					"99. Sortir";
		}
		
		public String toString() {
	
			return this.code +" -> "+ this.designation +"("+ this.price+" €) c'est une pizza de type =>"+this.categorie ;
		}
		/**
		 * 
		 * @param  Affiche le tab de pizza
		 */
		
}
