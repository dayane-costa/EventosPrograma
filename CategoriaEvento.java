public enum CategoriaEvento {
    FESTA("Festa"),
    EVENTO_ESPORTIVO("Evento Esportivo"),
    SHOW("Show"),
    CONFERENCIA("Conferência"),
    WORKSHOP("Workshop"),
    EXPOSICAO("Exposição"),
    TEATRO("Teatro"),
    CINEMA("Cinema"),
    GASTRONOMIA("Gastronomia"),
    CULTURAL("Cultural"),
    RELIGIOSO("Religioso"),
    EDUCACIONAL("Educacional"),
    OUTROS("Outros");
    
    private final String descricao;
    
    CategoriaEvento(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
}

