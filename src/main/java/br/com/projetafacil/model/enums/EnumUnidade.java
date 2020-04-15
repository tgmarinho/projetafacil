package br.com.projetafacil.model.enums;

public enum EnumUnidade {

	
	UNITARIO("Unitário"),
	HORA("Hora"),
	M("M"),
	M2("M2"),
	M3("M3"),
	MES("MES"),
	M_POR_MES("M/MES"),
	M2_POR_MES("M2/MES"),
	M3_POR_MES("M3/MES"),
	UN("UN"),
	CEM_M("100M"),
	DEZ_M("10M"),
	L("L"),
	DEZOITO_L("18L"),
	KG("KG"),
	SEIS_KG("6KG"),
	CINQUENTA_KG("50KG"),
	DUZENTOS_KG("200KG"),
	DUZENTOS_CINQUENTA("250G"),
	TREZENTOS_10_ML("310ML"),
	CENTO("CENTO"),
	H("H"),
	CJ("CJ"),
	DM3("DM3"),
	FL("FL"),
	GL("GL"),
	JG("JG"),
	KW_POR_H("KW/H"),
	MIL("MIL"),
	PAR("PAR"),
	T("T"),
	VB("VB"),
	SC25KG("SC25KG"),
	M3XKM("M3XKM"),
	TXKM("TXKM"),
	CHI("CHI"),
	CHP("CHP");


	
	private String descricao;
	
	EnumUnidade(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
