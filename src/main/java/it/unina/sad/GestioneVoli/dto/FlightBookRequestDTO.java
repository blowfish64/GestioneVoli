package it.unina.sad.GestioneVoli.dto;

public class FlightBookRequestDTO {
	private Long numLuggageCabin;
	private Long numLuggageBulk;
	private String confirmEMail;
	private String firstName;
	private String familyName;
	private String cardId;
	private String uniqueId;
	private String creditCard;

	public Long getNumLuggageCabin() {
		return numLuggageCabin;
	}

	public void setNumLuggageCabin(Long numLuggageCabin) {
		this.numLuggageCabin = numLuggageCabin;
	}

	public Long getNumLuggageBulk() {
		return numLuggageBulk;
	}

	public void setNumLuggageBulk(Long numLuggageBulk) {
		this.numLuggageBulk = numLuggageBulk;
	}

	public String getConfirmEMail() {
		return confirmEMail;
	}

	public void setConfirmEMail(String confirmEMail) {
		this.confirmEMail = confirmEMail;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
}
