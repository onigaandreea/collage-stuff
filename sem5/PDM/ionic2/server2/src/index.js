// class Song {
//   constructor({
//     id,
//     artist,
//     duration,
//     title,
//     dateOfRelease,
//     hasFeaturedArtists,
//   }) {
//     this.id = id;
//     this.artist = artist;
//     this.duration = duration;
//     this.title = title;
//     this.dateOfRelease = dateOfRelease;
//     this.hasFeaturedArtists = hasFeaturedArtists;
//   }
// }

// const songs = [];
// s1 = new Song({
//   id: 1,
//   artist: "Bon Jovi",
//   duration: 4.34,
//   title: "It's my life",
//   dateOfRelease: new Date(2010, 3, 12),
//   hasFeaturedArtists: false,
// });
// s2 = new Song({
//   id: 2,
//   artist: "Imagine Dragons",
//   duration: 5.0,
//   title: "On top of the world",
//   dateOfRelease: new Date(2009, 4, 1),
//   hasFeaturedArtists: false,
// });
// s3 = new Song({
//   id: 3,
//   artist: "Linkin Park",
//   duration: 4.29,
//   title: "New divide",
//   dateOfRelease: new Date(2014, 5, 20),
//   hasFeaturedArtists: false,
// });
// s4 = new Song({
//   id: 4,
//   artist: "Queen (feat. David Bowie)",
//   duration: 5.1,
//   title: "Under Pressure",
//   dateOfRelease: new Date(2008, 7, 15),
//   hasFeaturedArtists: true,
// });

// songs.push(s1);
// songs.push(s2);
// songs.push(s3);
// songs.push(s4);

// // let lastUpdated = songs[songs.length - 1].date;
// let lastId = songs[songs.length - 1].id;
// // const pageSize = 10;

// const broadcast = (data) =>
//   wss.clients.forEach((client) => {
//     if (client.readyState === WebSocket.OPEN) {
//       client.send(JSON.stringify(data));
//     }
//   });

// const router = new Router();

// router.get("/songs", (ctx) => {
//   console.log(songs);
//   ctx.response.body = songs;
//   ctx.response.status = 200;
// });

// // router.get("/item/:id", async (ctx) => {
// //   const itemId = ctx.request.params.id;
// //   const item = songs.find((item) => itemId === item.id);
// //   if (item) {
// //     ctx.response.body = item;
// //     ctx.response.status = 200; // ok
// //   } else {
// //     ctx.response.body = { message: `item with id ${itemId} not found` };
// //     ctx.response.status = 404; // NOT FOUND (if you know the resource was deleted, then return 410 GONE)
// //   }
// // });

// const createSong = async (ctx) => {
//   const song = ctx.request.body;
//   if (!song.title || !song.artist) {
//     // validation
//     ctx.response.body = { message: "Title or artist is missing" };
//     ctx.response.status = 400; //  BAD REQUEST
//     return;
//   }
//   if (!song.duration) {
//     // validation
//     ctx.response.body = { message: "Duration is not a number!" };
//     ctx.response.status = 400; //  BAD REQUEST
//     return;
//   }
//   song.id = lastId + 1;
//   lastId = song.id;
//   //song.hasFeaturedArtists = false;
//   songs.push(song);
//   ctx.response.body = song;
//   ctx.response.status = 201; // CREATED
//   console.log(song);
//   broadcast({
//     event: "created",
//     payload: { updatedSong: song },
//   });
// };

// router.post("/song", async (ctx) => {
//   await createSong(ctx);
// });

// router.put("/song/:id", async (ctx) => {
//   const id = parseInt(ctx.params.id);
//   const song = ctx.request.body;
//   const itemId = song.id;
//   console.log(song);
//   if (itemId && id !== song.id) {
//     ctx.response.body = { message: `Param id and body id should be the same` };
//     ctx.response.status = 400; // BAD REQUEST
//     return;
//   }
//   if (!song.duration) {
//     // validation
//     ctx.response.body = { message: "Duration is not a number!" };
//     ctx.response.status = 400; //  BAD REQUEST
//     return;
//   }

//   const index = songs.findIndex((item) => item.id === id);
//   if (index === -1) {
//     ctx.response.body = { issue: [{ error: `item with id ${id} not found` }] };
//     ctx.response.status = 400; // BAD REQUEST
//     return;
//   }
//   const itemVersion = parseInt(ctx.request.get("ETag")) || song.version;
//   if (itemVersion < songs[index].version) {
//     ctx.response.body = { issue: [{ error: `Version conflict` }] };
//     ctx.response.status = 409; // CONFLICT
//     return;
//   }
//   song.version++;
//   songs[index] = song;
//   ctx.response.body = song;
//   ctx.response.status = 200; // OK
//   broadcast({
//     event: "updated",
//     payload: { successMessage: "Updated song!", updatedSong: song },
//   });
// });

// router.del("/song/:id", (ctx) => {
//   const id = parseInt(ctx.params.id);
//   console.log(id);
//   const index = songs.findIndex((item) => id === item.id);
//   console.log(index);
//   if (index !== -1) {
//     const item = songs[index];
//     songs.splice(index, 1);
//     lastUpdated = new Date();
//     console.log(item);
//     broadcast({ event: "deleted", payload: { updatedSong: item } });
//   }
//   ctx.response.status = 204; // no content
// });

// // setInterval(() => {
// //   lastUpdated = new Date();
// //   lastId = `${parseInt(lastId) + 1}`;
// //   const item = new Song({
// //     id: lastId,
// //     text: `item ${lastId}`,
// //     date: lastUpdated,
// //     version: 1,
// //   });
// //   songs.push(item);
// //   console.log(`New item: ${item.text}`);
// //   broadcast({ event: "created", payload: { item } });
// // }, 5000);

// app.use(router.routes());
// app.use(router.allowedMethods());

// server.listen(3000);

import http from "http";
import Koa from "koa";
import WebSocket from "ws";
import Router from "koa-router";
import bodyParser from "koa-bodyparser";
import jwt from "koa-jwt";
import cors from "@koa/cors";
import { jwtConfig, timingLogger, exceptionHandler } from "./utils.js";
import { initWss } from "./wss.js";
import { itemRouter } from "./item.js";
import { authRouter } from "./auth.js";

const app = new Koa();
const server = http.createServer(app.callback());
const wss = new WebSocket.Server({ server });
initWss(wss);

app.use(cors());
app.use(timingLogger);
app.use(exceptionHandler);
app.use(bodyParser());

const prefix = "/api";

// public
const publicApiRouter = new Router({ prefix });
publicApiRouter.use("/auth", authRouter.routes());
app.use(publicApiRouter.routes()).use(publicApiRouter.allowedMethods());

app.use(jwt(jwtConfig));

// protected
const protectedApiRouter = new Router({ prefix });
protectedApiRouter.use("/song", itemRouter.routes());
app.use(protectedApiRouter.routes()).use(protectedApiRouter.allowedMethods());

server.listen(3000);
console.log("started on port 3000");
