export interface ContaBancaria{
    id: number;
    nome: string;
    numeroConta: string;
    agencia: string;
    chequeEspecialLiberado: boolean;
    saldo: number;
    chequeEspecial: number;
    taxa: number;
    agenciaNumero: string;
    valorChequeEspecialDiaSeguinte: string
}