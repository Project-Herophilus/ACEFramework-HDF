create table if not exists audit
(

    messageprocesseddate varchar(256),
    messageprocessedtime varchar(256),
    processingtype varchar(256),
    industrystd varchar(256),
    component varchar(256),
    messagetrigger varchar(256),
    processname varchar(256),
    auditdetails varchar(256),
    camelid varchar(256),
    exchangeid varchar(256),
    internalmsgid varchar(256),
    bodydata text,
    auditentiremessage text,
    "auditmessageID" bigserial not null
    constraint "PK_audit"
    primary key
    );
