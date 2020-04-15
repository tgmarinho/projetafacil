package br.com.projetafacil.service.material;

import br.com.projetafacil.model.ComposicaoServico;
import br.com.projetafacil.model.Estado;
import br.com.projetafacil.model.ItemComposicaoInsumo;
import br.com.projetafacil.model.ItemOrcamento;
import br.com.projetafacil.model.enums.TipoInsumo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DetalheMaterialService {

    private List<DetalheMaterialBO> materiaisDetalhados = new ArrayList<>();


    private List<ItemOrcamento> itensOrcamento;
    private List<ComposicaoServico> composicoes;
    private Estado estado;


    public List<DetalheMaterialBO> listarMateriaisDoOrcamento(List<ItemOrcamento> itensOrcamento, Estado estado) {

        this.itensOrcamento = itensOrcamento;
        this.estado = estado;

        this.composicoes = buscarComposicaoDosItensDeOrcamento(itensOrcamento);

        detalharMaterialDasComposicoes();


        return materiaisDetalhados;
    }


    private void detalharMaterialDasComposicoes() {
        composicoes.forEach(item -> {
            Optional<ItemOrcamento> itemOrcamento = buscarItemOrcamentoPorComposicaoServico(item, itensOrcamento);
            ItemOrcamento itemOrcamentoDaComposicao;
            if (itemOrcamento.isPresent()) {
                itemOrcamentoDaComposicao = itemOrcamento.get();
            } else {
                throw new RuntimeException("Item Orcamento não encontrado para a composição: " + item);
            }
            item.getInsumos().forEach(itemComposicaoInsumo -> {
                if (itemComposicaoInsumo.getTipoInsumo() != TipoInsumo.COMPOSICAO
                                                    && itemComposicaoInsumo.getInsumo().getTipo() == TipoInsumo.MATERIAL) {
                    criaDetalheMaterialDM(itemComposicaoInsumo, itemOrcamentoDaComposicao, estado, BigDecimal.ONE, false);
                }
                if (itemComposicaoInsumo.getComposicaoDaComposicao() != null) {
                    criaDetalheMaterialDasComposicoesDaComposicao(itemComposicaoInsumo.getComposicaoDaComposicao(),
                                            itemOrcamentoDaComposicao, itemComposicaoInsumo.getValorCoeficiente());
                }
            });
        });

    }

    private void criaDetalheMaterialDasComposicoesDaComposicao(ComposicaoServico compDaComp,
                                                                        ItemOrcamento itemOrcamento, BigDecimal valorCoeficienteDaComposicaoPai) {
        compDaComp.getInsumos().forEach(item -> {
            if (item.getTipoInsumo() != TipoInsumo.COMPOSICAO && item.getInsumo().getTipo() == TipoInsumo.MATERIAL) {

                criaDetalheMaterialDM(item, itemOrcamento, estado, valorCoeficienteDaComposicaoPai, true);
            }
            if (item.getComposicaoDaComposicao() != null) {
                criaDetalheMaterialDasComposicoesDaComposicao(item.getComposicaoDaComposicao(), itemOrcamento,
                                                        item.getValorCoeficiente().multiply(valorCoeficienteDaComposicaoPai));
            }
        });
    }


    private List<ComposicaoServico> buscarComposicaoDosItensDeOrcamento(List<ItemOrcamento> itensOrcamento) {
        return itensOrcamento.stream().map(ItemOrcamento::getComposicaoServico).collect(Collectors.toList());
    }


    private Optional<ItemOrcamento> buscarItemOrcamentoPorComposicaoServico(ComposicaoServico composicaoServico, List<ItemOrcamento> itensOrcamento) {
        return itensOrcamento.stream()
                .filter(i -> i.getComposicaoServico().equals(composicaoServico))
                .findAny();
    }


    private void criaDetalheMaterialDM(ItemComposicaoInsumo itemComposicaoInsumo,
                                       ItemOrcamento itemOrcamento, Estado estado, BigDecimal valorCoeficienteDaComposicaoPai, Boolean temPai) {

        try {

            Optional<DetalheMaterialBO> dmOptional = materiaisDetalhados.stream()
                    .filter(material -> material.getId().equals(itemComposicaoInsumo.getInsumo().getId()))
                    .findAny();

            if (valorCoeficienteDaComposicaoPai == null) {
                valorCoeficienteDaComposicaoPai = BigDecimal.ZERO;
            }


            DetalheMaterialBO dm;
            if (dmOptional.isPresent()) {
                dm = dmOptional.get();
                System.out.println("++++++++");
                System.out.println("ATUALIZANDO o valor do insumo ja existente =  " + dm.getMaterial());
                System.out.println(valorCoeficienteDaComposicaoPai);
                System.out.println(dm.getQuantidade());

                dm.setQuantidade(valorCoeficienteDaComposicaoPai.add(dm.getQuantidade()));
                calculaValorTotalSinapi(dm.getValorSinapi(), dm);
//                dm.setQuantidade(itemComposicaoInsumo.getValorCoeficiente().multiply(itemOrcamento.getQuantidade())
//                            .setScale(2, BigDecimal.ROUND_HALF_EVEN).add(dmOptional.get().getQuantidade()));
//
//
//                dm.setQuantidade(valorCoeficienteDaComposicaoPai.add(dm.getQuantidade()));


                System.out.println("ATUALIZANDO: " + dm.getQuantidade());
            } else {


                dm = new DetalheMaterialBO();
                dm.setId(itemComposicaoInsumo.getInsumo().getId());
                dm.setCodigoBIM(itemComposicaoInsumo.getInsumo().getCodigoBIM());
                dm.setMaterial(itemComposicaoInsumo.getInsumo().getNome());
                dm.setUnidade(itemComposicaoInsumo.getInsumo().getUnidade().getDescricao());
                dm.setQuantidade(itemComposicaoInsumo.getValorCoeficiente().multiply(itemOrcamento.getQuantidade()).setScale(2, BigDecimal.ROUND_HALF_EVEN));
//                if(temPai) {
                    dm.setQuantidade(valorCoeficienteDaComposicaoPai.multiply(dm.getQuantidade()));
//                }

                BigDecimal valorSinapi;
                // Pega o valor SINAPI referente ao estado selecionado no orçcamento
                valorSinapi = (BigDecimal) itemComposicaoInsumo.getInsumo().getClass()
                        .getDeclaredMethod("getValor" + estado.getSigla())
                        .invoke(itemComposicaoInsumo.getInsumo());
                if (valorSinapi != null) {
                    dm.setValorSinapi(valorSinapi);
                    calculaValorTotalSinapi(valorSinapi, dm);
//                    dm.setValorTotalSinapi(valorSinapi.multiply(dm.getQuantidade()).setScale(2, BigDecimal.ROUND_HALF_EVEN));
                } else {
                    dm.setValorSinapi(BigDecimal.ZERO);
                    calculaValorTotalSinapi(BigDecimal.ZERO, dm);
//                    dm.setValorTotalSinapi(BigDecimal.ZERO);
                }
                System.out.println("---------");
                System.out.println("NOVO Valor insumo recem criado = "  + dm.getMaterial());
                System.out.println(valorCoeficienteDaComposicaoPai);
                System.out.println(dm.getQuantidade());
                materiaisDetalhados.add(dm);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void calculaValorTotalSinapi(BigDecimal valorSinapi, DetalheMaterialBO dm){
        dm.setValorTotalSinapi(valorSinapi.multiply(dm.getQuantidade()).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    }

}