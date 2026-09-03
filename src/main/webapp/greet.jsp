<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Zoro Core Console | Enterprise Portal</title>
    
    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        zoro: {
                            50: '#ecfdf5',
                            500: '#10b981',
                            600: '#059669',
                            900: '#064e3b',
                            950: '#022c22'
                        }
                    }
                }
            }
        }
    </script>
    
    <!-- Google Fonts & FontAwesome -->
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@300;400;500;600;700;800&family=JetBrains+Mono:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        body { font-family: 'Plus Jakarta Sans', sans-serif; }
        .font-mono { font-family: 'JetBrains Mono', monospace; }
        .glow-border {
            box-shadow: 0 0 25px -5px rgba(16, 185, 129, 0.25);
        }
    </style>
</head>
<body class="bg-[#0b0f19] text-slate-100 min-h-screen flex flex-col justify-between antialiased selection:bg-emerald-500 selection:text-black">

    <!-- Top Navigation Header -->
    <header class="sticky top-0 z-50 bg-[#0b0f19]/80 backdrop-blur-xl border-b border-slate-800/80">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div class="flex items-center justify-between h-20">
                <!-- Branding -->
                <div class="flex items-center space-x-4">
                    <div class="w-11 h-11 rounded-2xl bg-gradient-to-tr from-emerald-500 to-teal-400 flex items-center justify-center shadow-lg shadow-emerald-500/20">
                        <i class="fa-solid fa-terminal text-slate-950 text-xl font-black"></i>
                    </div>
                    <div>
                        <div class="flex items-center space-x-2">
                            <span class="text-xl font-extrabold tracking-tight text-white">ZORO<span class="text-emerald-400">.CORE</span></span>
                            <span class="text-[10px] uppercase font-bold tracking-widest px-2 py-0.5 rounded-md bg-emerald-500/10 text-emerald-400 border border-emerald-500/30">Enterprise</span>
                        </div>
                        <p class="text-xs text-slate-400 font-mono">Handler: ZoroController.java</p>
                    </div>
                </div>

                <!-- Live System Indicator -->
                <div class="flex items-center space-x-6">
                    <div class="hidden md:flex items-center space-x-2 text-xs font-mono bg-slate-900/90 border border-slate-800 px-3 py-1.5 rounded-lg text-slate-300">
                        <span class="w-2 h-2 rounded-full bg-emerald-400 animate-ping"></span>
                        <span>SERVER TIME: ${timestamp}</span>
                    </div>
                    
                    <div class="flex items-center space-x-3 bg-slate-900 border border-slate-800 rounded-2xl py-1.5 px-3">
                        <div class="w-8 h-8 rounded-xl bg-gradient-to-br from-emerald-400 to-emerald-600 flex items-center justify-center text-slate-950 font-bold text-xs uppercase shadow-sm">
                            ${not empty userName ? userName.substring(0, 2) : "JS"}
                        </div>
                        <div class="text-left hidden sm:block">
                            <p class="text-xs font-bold text-white leading-none">${userName}</p>
                            <span class="text-[10px] text-emerald-400 font-medium">${tier}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Main Workspace -->
    <main class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8 w-full flex-grow space-y-8">

        <!-- Hero Greeting & Interactive Command Bar -->
        <div class="relative overflow-hidden rounded-3xl bg-gradient-to-br from-slate-900 via-[#0d1527] to-[#06201a] border border-emerald-500/20 p-8 sm:p-12 glow-border">
            <div class="absolute -right-20 -top-20 w-96 h-96 bg-emerald-500/10 rounded-full blur-3xl pointer-events-none"></div>
            
            <div class="relative z-10 grid grid-cols-1 lg:grid-cols-12 gap-8 items-center">
                
                <!-- Greeting Text Display -->
                <div class="lg:col-span-7 space-y-4">
                    <div class="inline-flex items-center space-x-2 px-3 py-1 rounded-full bg-emerald-950/80 border border-emerald-500/30 text-emerald-300 text-xs font-semibold">
                        <i class="fa-solid fa-circle-check text-emerald-400"></i>
                        <span>ZoroController Connected Successfully</span>
                    </div>

                    <h1 class="text-3xl sm:text-5xl font-extrabold text-white tracking-tight leading-tight">
                        <span class="bg-gradient-to-r from-emerald-300 via-teal-200 to-white bg-clip-text text-transparent">
                            ${wish}
                        </span>
                    </h1>

                    <p class="text-slate-300 text-sm sm:text-base leading-relaxed">
                        Currently running on embedded Tomcat with active Spring IoC dependency injection across service layers.
                    </p>
                </div>

                <!-- Interactive Query Customizer (Sends data back to ZoroController) -->
                <div class="lg:col-span-5 bg-slate-950/80 backdrop-blur-md border border-slate-800 p-6 rounded-2xl">
                    <h2 class="text-sm font-bold text-white uppercase tracking-wider mb-4 flex items-center">
                        <i class="fa-solid fa-sliders text-emerald-400 mr-2"></i>
                        Request Parameters
                    </h2>
                    
                    <form action="/greeting" method="GET" class="space-y-4">
                        <div>
                            <label class="block text-xs font-mono text-slate-400 mb-1">USER_NAME PARAM</label>
                            <input type="text" name="userName" value="${userName}" placeholder="Enter name..." 
                                class="w-full bg-slate-900 border border-slate-700 focus:border-emerald-500 rounded-xl px-4 py-2.5 text-sm text-white focus:outline-none transition font-medium" />
                        </div>
                        
                        <div>
                            <label class="block text-xs font-mono text-slate-400 mb-1">DEVELOPER ROLE / TIER</label>
                            <select name="tier" class="w-full bg-slate-900 border border-slate-700 focus:border-emerald-500 rounded-xl px-4 py-2.5 text-sm text-white focus:outline-none transition font-medium">
                                <option value="Lead Backend Architect" ${tier == 'Lead Backend Architect' ? 'selected' : ''}>Lead Backend Architect</option>
                                <option value="Master Developer" ${tier == 'Master Developer' ? 'selected' : ''}>Master Developer</option>
                                <option value="Spring Boot Specialist" ${tier == 'Spring Boot Specialist' ? 'selected' : ''}>Spring Boot Specialist</option>
                            </select>
                        </div>

                        <button type="submit" class="w-full py-3 bg-gradient-to-r from-emerald-600 to-teal-600 hover:from-emerald-500 hover:to-teal-500 text-slate-950 font-bold text-sm rounded-xl shadow-lg shadow-emerald-600/20 transition duration-200 flex items-center justify-center space-x-2">
                            <i class="fa-solid fa-paper-plane"></i>
                            <span>Execute Controller Route</span>
                        </button>
                    </form>
                </div>
            </div>
        </div>

        <!-- Telemetry Stats Cards -->
        <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
            
            <div class="bg-slate-900/60 border border-slate-800 rounded-2xl p-5 hover:border-emerald-500/30 transition">
                <div class="flex items-center justify-between text-slate-400 mb-3 text-xs uppercase font-semibold">
                    <span>Controller Class</span>
                    <i class="fa-solid fa-code text-emerald-400"></i>
                </div>
                <div class="text-xl font-bold text-white font-mono">ZoroController</div>
                <div class="text-xs text-emerald-400 mt-1 font-mono">@Controller active</div>
            </div>

            <div class="bg-slate-900/60 border border-slate-800 rounded-2xl p-5 hover:border-emerald-500/30 transition">
                <div class="flex items-center justify-between text-slate-400 mb-3 text-xs uppercase font-semibold">
                    <span>JVM Heap Memory</span>
                    <i class="fa-solid fa-microchip text-emerald-400"></i>
                </div>
                <div class="text-xl font-bold text-white font-mono">${usedMemory} MB <span class="text-xs text-slate-400 font-normal">/ ${maxMemory} MB</span></div>
                <div class="w-full bg-slate-800 h-1.5 rounded-full mt-2 overflow-hidden">
                    <div class="bg-emerald-400 h-full rounded-full" style="width: ${(usedMemory / maxMemory) * 100}%"></div>
                </div>
            </div>

            <div class="bg-slate-900/60 border border-slate-800 rounded-2xl p-5 hover:border-emerald-500/30 transition">
                <div class="flex items-center justify-between text-slate-400 mb-3 text-xs uppercase font-semibold">
                    <span>Active JVM Threads</span>
                    <i class="fa-solid fa-network-wired text-emerald-400"></i>
                </div>
                <div class="text-xl font-bold text-white font-mono">${activeThreads} Threads</div>
                <div class="text-xs text-slate-400 mt-1">Multi-threaded pool</div>
            </div>

            <div class="bg-slate-900/60 border border-slate-800 rounded-2xl p-5 hover:border-emerald-500/30 transition">
                <div class="flex items-center justify-between text-slate-400 mb-3 text-xs uppercase font-semibold">
                    <span>View Engine</span>
                    <i class="fa-solid fa-cubes text-emerald-400"></i>
                </div>
                <div class="text-xl font-bold text-white font-mono">Tomcat Jasper</div>
                <div class="text-xs text-emerald-400 mt-1 font-mono">greet.jsp rendered</div>
            </div>
        </div>

        <!-- Registered Endpoints & Handler Architecture -->
        <div class="bg-slate-900/40 border border-slate-800/80 rounded-2xl p-6">
            <div class="flex items-center justify-between mb-6">
                <div>
                    <h3 class="text-base font-bold text-white">MVC Pipeline Architecture</h3>
                    <p class="text-xs text-slate-400">Request lifecycle mappings managed by Spring DispatcherServlet</p>
                </div>
                <span class="text-xs font-mono bg-emerald-500/10 text-emerald-400 border border-emerald-500/20 px-3 py-1 rounded-lg">HTTP 200 OK</span>
            </div>

            <div class="overflow-x-auto">
                <table class="w-full text-left text-xs font-mono">
                    <thead class="bg-slate-950/60 text-slate-400 uppercase text-[11px]">
                        <tr>
                            <th class="py-3 px-4 rounded-l-lg">Method</th>
                            <th class="py-3 px-4">Endpoint</th>
                            <th class="py-3 px-4">Controller Bean</th>
                            <th class="py-3 px-4">Injected Service</th>
                            <th class="py-3 px-4 rounded-r-lg">Target View</th>
                        </tr>
                    </thead>
                    <tbody class="divide-y divide-slate-800/60 text-slate-300">
                        <tr>
                            <td class="py-3.5 px-4 text-emerald-400 font-bold">GET</td>
                            <td class="py-3.5 px-4 text-white font-bold">/greeting</td>
                            <td class="py-3.5 px-4 text-emerald-300">ZoroController.generateWish()</td>
                            <td class="py-3.5 px-4 text-slate-400">IGreetingService (GreetingService.java)</td>
                            <td class="py-3.5 px-4 text-indigo-400 font-bold">greet.jsp</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

    </main>

    <!-- Console Footer -->
    <footer class="border-t border-slate-800/60 py-6 text-center text-xs text-slate-500 font-mono">
        <p>ZORO ENTERPRISE CORE • SPRING BOOT 3.2.5 • JAVA 17 RUNTIME</p>
    </footer>

</body>
</html>