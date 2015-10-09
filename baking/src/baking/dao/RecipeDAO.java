package baking.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import baking.model.Recipe;

/**
 * A data access object (DAO) providing persistence and search support for
 * Recipe entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see baking.model.Recipe
 * @author MyEclipse Persistence Tools
 */
@Repository
public class RecipeDAO extends BaseDao {
	private static final Logger log = LoggerFactory.getLogger(RecipeDAO.class);
	// property constants
	public static final String GOODS_ID = "goodsId";
	public static final String INVENT_ID = "inventId";
	public static final String STEP_ID = "stepId";
	public static final String USAGE = "usage";
	public static final String SHOW_ORDER = "showOrder";

	public void save(Recipe transientInstance) {
		log.debug("saving Recipe instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Recipe persistentInstance) {
		log.debug("deleting Recipe instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Recipe findById(java.lang.Integer id) {
		log.debug("getting Recipe instance with id: " + id);
		try {
			Recipe instance = (Recipe) getSession().get("baking.model.Recipe",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Recipe instance) {
		log.debug("finding Recipe instance by example");
		try {
			List results = getSession().createCriteria("baking.model.Recipe")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Recipe instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Recipe as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGoodsId(Object goodsId) {
		return findByProperty(GOODS_ID, goodsId);
	}

	public List findByInventId(Object inventId) {
		return findByProperty(INVENT_ID, inventId);
	}

	public List findByStepId(Object stepId) {
		return findByProperty(STEP_ID, stepId);
	}

	public List findByUsage(Object usage) {
		return findByProperty(USAGE, usage);
	}

	public List findByShowOrder(Object showOrder) {
		return findByProperty(SHOW_ORDER, showOrder);
	}

	public List findAll() {
		log.debug("finding all Recipe instances");
		try {
			String queryString = "from Recipe";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Recipe merge(Recipe detachedInstance) {
		log.debug("merging Recipe instance");
		try {
			Recipe result = (Recipe) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Recipe instance) {
		log.debug("attaching dirty Recipe instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Recipe instance) {
		log.debug("attaching clean Recipe instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}