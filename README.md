package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp01.response;

import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp02.response.dto.Bp01DataResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.TrxPEPFDataResponse.dto.PepfHeaderResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.TrxPEPFDataResponse.dto.PepfNoticeResponseDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBp01Response {
    private Bp01DataResponseDTO data;
    private PepfHeaderResponseDTO cabecera;
    private Object autorizacion;
    private Map<String, Object> paginacion;
    private List<PepfNoticeResponseDTO> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private boolean ok;

    public Bp01DataResponseDTO getData() {
        return data;
    }

    public void setData(Bp01DataResponseDTO data) {
        this.data = data;
    }

    public PepfHeaderResponseDTO getCabecera() {
        return cabecera;
    }

    public void setCabecera(PepfHeaderResponseDTO cabecera) {
        this.cabecera = cabecera;
    }

    public Object getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Object autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Map<String, Object> getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(Map<String, Object> paginacion) {
        this.paginacion = paginacion;
    }

    public List<PepfNoticeResponseDTO> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<PepfNoticeResponseDTO> avisos) {
        this.avisos = avisos;
    }

    public List<ErrorTrxDTO> getErrores() {
        return errores;
    }

    public void setErrores(List<ErrorTrxDTO> errores) {
        this.errores = errores;
    }

    public Object getConexion() {
        return conexion;
    }

    public void setConexion(Object conexion) {
        this.conexion = conexion;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
