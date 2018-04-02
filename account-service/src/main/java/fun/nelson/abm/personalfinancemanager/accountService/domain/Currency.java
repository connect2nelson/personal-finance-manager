package fun.nelson.abm.personalfinancemanager.accountService.domain;

public enum Currency {

	USD, EUR, RUB;

	public static Currency getDefault() {
		return EUR;
	}
}
