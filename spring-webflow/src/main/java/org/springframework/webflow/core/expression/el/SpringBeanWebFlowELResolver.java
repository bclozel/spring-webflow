package org.springframework.webflow.core.expression.el;

import javax.el.ELContext;
import javax.el.ELException;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;

/**
 * EL resolver for Spring Beans accessible to the flow's local bean factory.
 * 
 * @author Jeremy Grelle
 * 
 * TODO - Extend SpringBeanELResolver when Spring 2.5.2 is available.
 */
public class SpringBeanWebFlowELResolver extends SpringBeanFacesELResolver {

	private static final BeanFactory EMPTY_BEAN_FACTORY = new StaticListableBeanFactory();

	public Class getType(ELContext elContext, Object base, Object property) throws ELException {
		if (base != null && base instanceof RequestContext) {
			return super.getType(elContext, null, property);
		} else {
			return null;
		}
	}

	public Object getValue(ELContext elContext, Object base, Object property) throws ELException {
		if (base != null && base instanceof RequestContext) {
			return super.getValue(elContext, null, property);
		} else {
			return null;
		}
	}

	public boolean isReadOnly(ELContext elContext, Object base, Object property) throws ELException {
		if (base != null && base instanceof RequestContext) {
			return super.isReadOnly(elContext, null, property);
		} else {
			return false;
		}
	}

	public void setValue(ELContext elContext, Object base, Object property, Object value) throws ELException {
		if (base != null && base instanceof RequestContext) {
			super.setValue(elContext, null, property, value);
		}
	}

	protected BeanFactory getBeanFactory(ELContext elContext) {
		RequestContext rc = RequestContextHolder.getRequestContext();
		if (rc.getActiveFlow() instanceof BeanFactory) {
			return (BeanFactory) rc.getActiveFlow();
		} else {
			return EMPTY_BEAN_FACTORY;
		}
	}

}
