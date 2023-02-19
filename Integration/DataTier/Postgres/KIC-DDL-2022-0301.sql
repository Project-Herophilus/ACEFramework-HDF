create table if not exists audit_dataintegration
(
   intgrtn_insightID serial
        constraint intgrtn_insightID_pk
            primary key,
    messageprocesseddate varchar(25) null,
    messageprocessedtime varchar(25) null,
    processingtype varchar(25) null,
    industrystd varchar(20) null,
    component varchar(75) null,
    messagetrigger varchar(25) null,
    processname varchar(55) null,
    auditdetails varchar(199) null,
    camelid varchar(49) null,
    exchangeid varchar(49) null,
    internalmsgid varchar(49) null,
    auditentiremessage text null,
    bodydata text null,
    createddate timestamp default CURRENT_TIMESTAMP
);

create unique index if not exists intgrtn_insight_uindex
    on audit_dataintegration (intgrtn_insightID);

create table if not exists audit_appintegration
(
   appintgrtn_insightID serial
        constraint appintgrtn_insightID_pk
            primary key,
    messageprocesseddate varchar(25) null,
    messageprocessedtime varchar(25) null,
    processingtype varchar(25) null,
    industrystd varchar(20) null,
    component varchar(75) null,
    messagetrigger varchar(25) null,
    processname varchar(55) null,
    auditdetails varchar(199) null,
    camelid varchar(49) null,
    exchangeid varchar(49) null,
    internalmsgid varchar(49) null,
    errorid varchar(49) null,
    errordata text null,
    requesttype varchar(49) null,
    transactioncount varchar(7),
    transactiondirection varchar(49) null,
    auditentiremessage text null,
    bodydata text null,
    createddate timestamp default CURRENT_TIMESTAMP
);

create unique index if not exists appintgrtn_insight_uindex
    on audit_appintegration (appintgrtn_insightID);