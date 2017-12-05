package ${packageName};

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
${importStr}

/**
 *
 * ${description}业务类接口
 *
 * @version
 * <pre>
 * Created by ${author} on ${createDate}.
 * </pre>
 * @since 1.0
 */
@Service
public class ${className} implements ${serviceInterfaceName} {

    /**
     * ${description}数据接口
     */
    @Autowired
    private ${daoClassName} ${daoName};

    /**
     * <p>
     * Description : 插入${description}<br />
     * </p>
     *
     * @param ${entityName}
     *
     * @return
     *
     * @date ${createDate}
     */
    @Override
    public void insert(${entityClassName} ${entityName}){
        ${daoName}.insert(${entityName});
    }

    /**
     * <p>
     * Description : 更新${description}<br />
     * </p>
     *
     * @param ${entityName}
     *
     * @return
     *
     * @date ${createDate}
     */
    @Override
    public void updateByKey(${entityClassName} ${entityName}){
        ${daoName}.updateByKey(${entityName});
    }

    /**
     * <p>
     * Description : 根据ID获取${description}记录<br />
     * </p>
     *
     * @param id
     *
     * @return
     *
     * @date ${createDate}
     */
    @Override
    ${entityClassName} selectByKey(@Param(value = "id") String id) {
        return ${daoName}.selectByKey(id);
    }

    /**
     * <p>
     * Description : 根据id删除${description}记录<br />
     * </p>
     *
     * @param id
     *
     * @date ${createDate}
     */
    @Override
    public void deleteByKey(String id) {
        ${daoName}.deleteByKey(id);
    }

    /**
     * <p>
     * Description : 模糊匹配，分页查询${description}记录列表<br />
     * </p>
     *
     * @param ${entityName}
     *
     * @param offset
     *
     * @param limit
     *
     * @return
     *
     * @date ${createDate}
     */
    @Override
    public List<${entityClassName}> likePage(${entityClassName} ${entityName}, int offset, int limit) {
        return ${daoName}.likePage(${entityName} , new RowBounds(offset, limit));
    }

    /**
     * <p>
     * Description : 模糊匹配，不进行分页<br />
     * </p>
     *
     * @param ${entityName}
     *
     * @return
     *
     * @date ${createDate}
     */
    @Override
    public List<${entityClassName}> like(${entityClassName} ${entityName}) {
        return ${daoName}.like(${entityName});
    }


    /**
     * <p>
     * Description : 获取${description}记录总条数<br />
     * </p>
     *
     * @param ${entityName}
     *
     * @return
     *
     * @date ${createDate}
     */
    @Override
    public int rows(${entityClassName} ${entityName}) {
        return ${daoName}.rows(${entityName});
    }
}