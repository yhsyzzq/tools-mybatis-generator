<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
		"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- ${description}命名空间 -->
<mapper namespace="${namespace}">
	
	<resultMap id="${entityResultMap}" type="${entityFullName}">
		<id property="id" column="ID" />
		${resultMapStr}
	</resultMap>
	
	<!-- 表字段 -->
	<sql id="column">
		${columnStr}
	</sql>

	<!-- Where模糊匹配字段 -->
	<sql id="like">
		${likeStr}
	</sql>

	<!-- Where精确匹配字段 -->
	<sql id="equal">
		${equalStr}
	</sql>
  
	<!-- 插入单条数据 -->
	<insert id="insert" parameterType="${entityFullName}">
		INSERT INTO ${tableName}(
			${insertColumnNameStr}
		)VALUES(
			${insertColumnStr}
		)
	</insert>

	<!-- 根据主键修改数据持久化对象 -->
	<update id="updateByKey" parameterType="${entityFullName}">
		UPDATE ${tableName}
		<set>
			${updateColumnStr}
		</set>
		<where>
			<if test="id != null and id != ''">
				ID = <#noparse>#{id}</#noparse>
			</if>
		</where>
	</update>
  
	<!-- 根据主键删除数据持久化对象 -->
	<delete id="deleteByKey">
		DELETE FROM ${tableName}
		<where>
			<if test="id != null and id != ''">
				ID = <#noparse>#{id}</#noparse>
			</if>
		</where>
	</delete>
  
	<!-- 根据主键查询并返回数据持久化对象 -->
	<select id="selectByKey" resultMap="${entityResultMap}">
		SELECT
		<include refid="column" />
		FROM ${tableName}
		<where>
			<if test="id != null and id != ''">
				ID = <#noparse>#{id}</#noparse>
			</if>
		</where>
	</select>
	
	<!-- 查询所有数据 -->
	<select id="list" parameterType="${entityFullName}" resultMap="${entityResultMap}">
		SELECT
		<include refid="column" />
		FROM ${tableName}
		<where>
			<include refid="equal" />
		</where>
	</select>
  
	<!-- 分页查询数据 -->
	<select id="listPage" parameterType="${entityFullName}" resultMap="${entityResultMap}">
		SELECT
		<include refid="column" />
		FROM ${tableName}
		<where>
			<include refid="equal" />
		</where>
	</select>
  
	<!-- 模糊查询 -->
	<select id="like" parameterType="${entityFullName}" resultMap="${entityResultMap}">
		SELECT
		<include refid="column" />
		FROM ${tableName}
		<where>
			<include refid="like" />
		</where>
	</select>
  
	<!-- 模糊分页查询 -->
	<select id="likePage" parameterType="${entityFullName}" resultMap="${entityResultMap}">
		SELECT
		<include refid="column" />
		FROM ${tableName}
		<where>
			<include refid="like" />
		</where>
	</select>

	<!-- 模糊查询统计行数 -->
	<select id="rows" resultType="Integer" parameterType="${entityFullName}">
		SELECT COUNT(1) AS _rows_ FROM ${tableName}
		<where>
			<include refid="like" />
		</where>
	</select>

</mapper>