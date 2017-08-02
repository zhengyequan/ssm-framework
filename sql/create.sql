drop table if exists Loupan_Info;
create table Loupan_Info(
	id int(11) not null AUTO_INCREMENT,
	name varchar(128), -- 楼盘名称
	subName varchar(256), -- 二级名称
	detailDesc text, -- 描述
	developer varchar(64), -- 开发商
	areaCode varchar(8), -- 所属地区编码
	indexImgs text, -- 首页图
	planImgs text, -- 规划图，jsonArray
	supportImgs text, -- 配套图， jsonArray
	effectImgs text , -- 效果图， jsonArray
	modelImgs text, -- 样板图， jsonArray
	layoutImgs text, -- 户型图， jsonArray
	link text, -- 链接
	lng decimal(15, 6), -- 经度
	lat decimal(15, 6), -- 纬度
	price varchar(256), -- 价格范围
	contactName varchar(64),  -- 联系人
	contactPhone varchar(16), -- 手机号
	contactLandLine varchar(16), -- 座机
	contactWX varchar(64), -- 微信
	contactQQ varchar(16), -- qq
	status smallint,	-- 售货中，已售罄
	gmtOpen date,
	gmtCreated datetime not null, -- 创建时间
	
	primary key(id)
);

drop table if exists Global_Config;
create table Global_Config(
	id int(11) not null AUTO_INCREMENT,
	domainName text not null,
	
	primary key(id)
);