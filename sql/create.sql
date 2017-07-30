drop table if exists Loupan_Info;
create table Loupan_Info(
	id int(11) not null AUTO_INCREMENT,
	name varchar(128), -- 楼盘名称
	subName varchar(256), -- 二级名称
	detailDesc text, -- 描述
	imgPath text, -- 图片路径，LinkedMap方式有序存储
	lng float not null, -- 经度
	lat float not null, -- 纬度
	price varchar(256), -- 价格范围
	contactName varchar(64),  -- 联系人
	contactPhone varchar(11), -- 手机号
	contactLandLine varchar(15), -- 座机
	contactWX varchar(64), -- 微信
	contactQQ varchar(15), -- qq
	status smallint,	-- 售货中，已售罄
	gmtCreated datetime not null, -- 创建时间
	
	primary key(id)
);