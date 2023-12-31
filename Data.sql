USE [master]
GO
/****** Object:  Database [Java4_WebPhim]    Script Date: 7/15/2023 12:46:50 AM ******/
CREATE DATABASE [Java4_WebPhim]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Java4_WebPhim', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\Java4_WebPhim.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Java4_WebPhim_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\Java4_WebPhim_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [Java4_WebPhim] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Java4_WebPhim].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Java4_WebPhim] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET ARITHABORT OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Java4_WebPhim] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Java4_WebPhim] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Java4_WebPhim] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Java4_WebPhim] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET RECOVERY FULL 
GO
ALTER DATABASE [Java4_WebPhim] SET  MULTI_USER 
GO
ALTER DATABASE [Java4_WebPhim] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Java4_WebPhim] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Java4_WebPhim] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Java4_WebPhim] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Java4_WebPhim] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'Java4_WebPhim', N'ON'
GO
ALTER DATABASE [Java4_WebPhim] SET QUERY_STORE = OFF
GO
USE [Java4_WebPhim]
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [Java4_WebPhim]
GO

CREATE TABLE [dbo].[user](
	[id] [int] PRIMARY KEY IDENTITY,
	[username] [varchar](20) UNIQUE NOT NULL,
	[password] [varchar](20) NOT NULL,
	[email] [varchar](50) UNIQUE NOT NULL,
	[isAdmin] [bit] NOT NULL DEFAULT 0,
	[isActive] [bit] NOT NULL DEFAULT 1,
)

CREATE TABLE [dbo].[video](
	[id] [int] PRIMARY KEY IDENTITY,
	[title] [nvarchar](255) NOT NULL,
	[href] [varchar](50) NOT NULL,
	[poster] [varchar](255) NULL,
	[views] [int] NOT NULL DEFAULT 0,
	[shares] [int] NOT NULL DEFAULT 0,
	[description] [nvarchar](255) NOT NULL,
	[isActive] [bit] NOT NULL DEFAULT 1,
 )

 CREATE TABLE [dbo].[history](
	[id] [int] PRIMARY KEY IDENTITY,
	[userId] [int] FOREIGN KEY REFERENCES [user](id),
	[videoId] [int] FOREIGN KEY REFERENCES [video](id),
	[viewedDate] [datetime] NOT NULL DEFAULT GETDATE(),
	[isLiked] [bit] NOT NULL DEFAULT 0,
	[likedDate] [datetime] NULL,
)

insert into [user](username, [password], email,isAdmin)
values 
('khangnd', '123', 'khangnd@gmail.com', 1),
('phucnt', '123', 'phucnt@gmail.com', 0)

insert into video(title, href, [description])
values 
(N'Đại chiến ninja lần 4 (nhạc remix)', 'SwgZB2VzxXE', N'Tổng hợp anime'),
(N'Luffy đánh bại Katakuri và cùng Penkom thoát ra thế giới gương', '7B-q5Eb6WYk', N'LK NHẠC REMIX | Băng mũ rơm Đảo bánh 14'),
(N'Giải đấu sức mạnh toàn vũ trụ Nhạc phim anime Goku vs jiren Dragon ball super', 'uYIeS0MkhUM', N'Nhạc phim anime Goku vs jiren Dragon ball super'),
(N'HỘI PHÁP SƯ HỎA LONG Fairy Tail ss2 phần 1 eater music', 'DBYUWgm1dWo', N'Fairy Tail ss2 phần 1 eater music')

insert into history(userId, videoId, isLiked, likedDate)
values
(1, 1, 1, GETDATE()),
(1, 3, 0, null),
(1, 4, 0, null),
(1, 2, 1, GETDATE())


go
create proc sp_selectUsersLikedVideoByVideoHref (@videoHref varchar(50))
as begin
	select
	u.id, u.username, u.username, u.[password], u.email, u.isAdmin, u.isActive
	from
	video v left join history h on v.id = h.videoId
	left join [user] u on h.userId = u.id
	where
	v.href = @videoHref and u.isActive = 1 and v.isActive = 1 and h.isLiked = 1
end

