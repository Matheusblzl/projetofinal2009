package br.com.buyFast.integration.dao.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.buyFast.integration.dao.SettingsDao;
import br.com.buyFast.model.Settings;

/**
 * Classe DAO que implementa a interface {@link SettingsDao}.
 */
@Repository
@Transactional(readOnly=true, propagation=Propagation.REQUIRES_NEW)
public class SettingsDaoImpl extends GenericDaoImpl<Settings, Integer> implements SettingsDao {

}